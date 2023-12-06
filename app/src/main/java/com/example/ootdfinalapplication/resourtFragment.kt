package com.example.ootdfinalapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.ootdfinalapplication.databinding.FragmentAdd2Binding
import com.example.ootdfinalapplication.databinding.FragmentResourtBinding


class resourtFragment : Fragment() {

    var binding : FragmentResourtBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentResourtBinding.inflate(inflater)
        // Inflate the layout for this fragment
        return binding?.root
    }
}