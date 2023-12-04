package com.example.ootdfinalapplication

import SharedViewModel
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.ootdfinalapplication.databinding.ActivityMainBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.PropertyName
import com.google.firestore.v1.StructuredAggregationQuery.Aggregation.Count


data class UserImageInfo(
    @PropertyName("userId")
    val userId: String = "",

    @PropertyName("imageDescription")
    val imageDescription: String = "",

    @PropertyName("imageUrl")
    val imageUrl: String = ""
)

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        FirebaseApp.initializeApp(this)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedViewModel = ViewModelProvider(this).get(SharedViewModel::class.java)
        //setSupportActionBar(binding.toolbar) // Add this line

        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),1)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.frg_nav) as NavHostFragment
        val navController = navHostFragment.navController

        /*
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.add2Fragment,
                R.id.entryFragment,
                R.id.lankFragment,
                R.id.myFragment,
                R.id.sourchFragment
            )
        )

         */

        binding.bottomNavigationView.setupWithNavController(navController)

        binding.bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            val selectedFragment = when (menuItem.itemId) {
                R.id.addFragment -> {
                    // 백 스택을 지우고 add2Fragment로 이동
                    navController.popBackStack(R.id.add2Fragment, false)
                    R.id.add2Fragment
                }
                R.id.entryFragment -> R.id.entryFragment
                R.id.lankFragment -> R.id.lankFragment
                R.id.myFragment -> R.id.myFragment
                R.id.sourchFragment -> R.id.sourchFragment
                else -> throw IllegalArgumentException("Invalid menu item ID")
            }

            // 백 스택 지우기
            navController.popBackStack(selectedFragment, false)

            // 선택된 프래그먼트로 이동
            navController.navigate(selectedFragment)

            true
        }
    }


        override fun onSupportNavigateUp(): Boolean {
            val navController =
                supportFragmentManager.findFragmentById(R.id.frg_nav)?.findNavController()
            return navController?.navigateUp() ?: super.onSupportNavigateUp()
        }
    }
