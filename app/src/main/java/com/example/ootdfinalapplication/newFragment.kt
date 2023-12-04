package com.example.ootdfinalapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.ootdfinalapplication.databinding.FragmentAdd2Binding
import com.example.ootdfinalapplication.databinding.FragmentLankBinding
import com.example.ootdfinalapplication.databinding.FragmentLoginBinding
import com.example.ootdfinalapplication.databinding.FragmentNewBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
/*
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

 */

class newFragment : Fragment() {
    /*
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

     */
    private var _binding : FragmentNewBinding? = null
    private val binding get() = _binding!!

    lateinit var auth: FirebaseAuth

    private  lateinit var bottomNavigationView: BottomNavigationView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewBinding.inflate(inflater)
        // Inflate the layout for this fragment
        bottomNavigationView = requireActivity().findViewById(R.id.bottomNavigationView)

        bottomNavigationView.visibility = View.GONE

        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()

        binding.creatBr.setOnClickListener {
            val email = binding.emailTxt.text.toString()
            val password = binding.passworldTxt.text.toString()

            // Firebase에 새로운 사용자 생성
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(requireActivity()) { task ->
                    if (task.isSuccessful) {
                        // 회원가입 성공
                        val user: FirebaseUser? = auth.currentUser
                        Snackbar.make(view, "회원가입 성공", Snackbar.LENGTH_SHORT).show()

                        // 로그인 화면으로 이동
                        findNavController().navigate(R.id.action_newFragment_to_loginFragment)

                    } else {
                        // 회원가입 실패
                        Snackbar.make(
                            view,
                            "회원가입 실패: ${task.exception?.message}",
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }
                }

        }
    }

}