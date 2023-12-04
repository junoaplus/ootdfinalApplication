package com.example.ootdfinalapplication

import com.example.ootdfinalapplication.viewmodel.Add2ViewModel
import com.example.ootdfinalapplication.viewmodel.UploadStatus


import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.ootdfinalapplication.databinding.FragmentAdd2Binding
import com.example.ootdfinalapplication.repository.Add2Repository

class add2Fragment : Fragment() {

    val repository = Add2Repository()
    private var selectedImageUri: Uri? = null
    private var binding: FragmentAdd2Binding? = null
    private val viewModel: Add2ViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAdd2Binding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.OOTDImg?.setOnClickListener {
            openGallery()
        }

        binding?.upload?.setOnClickListener {
            val description = binding?.addphotoEditExplain?.text.toString()
            viewModel.uploadImageToFirebase(selectedImageUri, description)
        }


        viewModel.uploadStatus.observe(viewLifecycleOwner, Observer { status ->
            when (status) {
                is UploadStatus.Success -> {
                    Toast.makeText(requireContext(), "이미지 업로드 성공", Toast.LENGTH_SHORT).show()
                }
                is UploadStatus.Failure -> {
                    Toast.makeText(requireContext(), status.errorMessage, Toast.LENGTH_SHORT).show()
                    
                }
            }
        })
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_IMAGE_PICK)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_PICK && resultCode == Activity.RESULT_OK && data != null) {
            selectedImageUri = data.data
            binding?.OOTDImg?.setImageURI(selectedImageUri)
        }
    }

    companion object {
        private const val REQUEST_IMAGE_PICK = 1
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
