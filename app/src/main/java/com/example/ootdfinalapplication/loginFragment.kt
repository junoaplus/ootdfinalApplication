package com.example.ootdfinalapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.ootdfinalapplication.databinding.FragmentLoginBinding
import com.example.ootdfinalapplication.repository.Add2Repository
import com.example.ootdfinalapplication.viewmodel.LoginViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth


class loginFragment : Fragment() {

    private lateinit var viewModel: LoginViewModel
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!


    private lateinit var auth: FirebaseAuth

    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        Add2Repository()
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




        binding.loginBt.setOnClickListener {
            val email = binding.mailTxt.text.toString()
            val password = binding.passTxt.text.toString()

            viewModel.signInWithEmailAndPassword(email, password)
        }

        FirebaseApp.initializeApp(requireContext())
        auth = FirebaseAuth.getInstance()

        bottomNavigationView = requireActivity().findViewById(R.id.bottomNavigationView)
        bottomNavigationView.visibility = View.GONE

        binding.newBt.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_newFragment)
        }

        binding.loginBt.setOnClickListener {
            val email = binding.mailTxt.text.toString()
            val password = binding.passTxt.text.toString()

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(requireActivity()) { task ->
                    if (task.isSuccessful) {
                        val user = auth.currentUser
                        Add2Repository().saveUserInfo(user)

                        findNavController().navigate(R.id.action_loginFragment_to_entryFragment)
                    } else {
                    }
                }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        val currentDestinationId = findNavController().currentDestination?.id

        if (currentDestinationId == R.id.entryFragment) {
            bottomNavigationView.visibility = View.VISIBLE
        } else {
            bottomNavigationView.visibility = View.GONE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
