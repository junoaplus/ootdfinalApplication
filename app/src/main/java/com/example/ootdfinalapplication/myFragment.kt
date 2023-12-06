package com.example.ootdfinalapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.ootdfinalapplication.databinding.FragmentAdd2Binding
import com.example.ootdfinalapplication.databinding.FragmentMyBinding
import com.example.ootdfinalapplication.repository.Add2Repository


class myFragment : Fragment() {

    var binding : FragmentMyBinding? = null
    val repository = Add2Repository()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyBinding.inflate(inflater)
        // Inflate the layout for this fragment
        return binding?.root
    }
}