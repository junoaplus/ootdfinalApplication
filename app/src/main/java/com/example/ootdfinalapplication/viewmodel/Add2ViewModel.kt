package com.example.ootdfinalapplication.viewmodel

import android.app.Application
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ootdfinalapplication.Model.ContentModel
import com.example.ootdfinalapplication.UserImageInfo
import com.example.ootdfinalapplication.repository.Add2Repository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.flow.combine

sealed class UploadStatus {
    object Success : UploadStatus()
    data class Failure(val errorMessage: String) : UploadStatus()
}

// Add2ViewModel
class Add2ViewModel(application: Application) : AndroidViewModel(application) {


    private val storageReference: StorageReference = FirebaseStorage.getInstance().reference.child("images")
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    private val _uploadStatus = MutableLiveData<UploadStatus>()
    val uploadStatus: LiveData<UploadStatus> get() = _uploadStatus


    fun uploadImageToFirebase(selectedImageUri: Uri?, imageDescription: String) {
        val currentUser = auth.currentUser


        selectedImageUri?.let { uri ->
            val imageRef = storageReference.child("${System.currentTimeMillis()}.jpg")
            val userId = currentUser?.uid
            imageRef.putFile(uri)
                .addOnSuccessListener { taskSnapshot ->
                    imageRef.downloadUrl.addOnSuccessListener { downloadUri ->
                        userId?.let { uid ->
                            val contentModel = ContentModel(uid, imageDescription, downloadUri.toString())
                            val userImageInfo = UserImageInfo(uid, imageDescription, downloadUri.toString())
                            saveImageInfoToDatabase(userImageInfo)
                        }
                    }
                    _uploadStatus.value = UploadStatus.Success
                }
                .addOnFailureListener { e ->
                    _uploadStatus.value = UploadStatus.Failure("이미지 업로드 실패: ${e.message}")
                }
        } ?: run {
            _uploadStatus.value = UploadStatus.Failure("이미지를 선택해주세요.")
        }
    }

    private fun saveImageInfoToDatabase(userImageInfo: UserImageInfo) {
        val firestore = FirebaseFirestore.getInstance()
        val contentModel = ContentModel(
            auth.uid.toString(),
            userImageInfo.imageDescription,
            userImageInfo.imageUrl.toString(),
            auth.currentUser?.email,
            System.currentTimeMillis()
        )
        firestore.collection("userImages")
            .add(contentModel)
            .addOnSuccessListener {
                Log.d("Add2ViewModel", "이미지 업로드 및 정보 저장 성공")
                _uploadStatus.value = UploadStatus.Success
            }
            .addOnFailureListener { e ->
                Log.e("Add2ViewModel", "이미지 정보 저장 실패: ${e.message}")
                _uploadStatus.value = UploadStatus.Failure("이미지 정보 저장 실패: ${e.message}")
            }
    }
}


