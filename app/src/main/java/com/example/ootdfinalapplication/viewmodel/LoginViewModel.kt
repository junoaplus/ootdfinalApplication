package com.example.ootdfinalapplication.viewmodel

import androidx.lifecycle.ViewModel
import com.example.ootdfinalapplication.repository.Add2Repository
import com.google.firebase.auth.FirebaseUser

class LoginViewModel(private val repository: Add2Repository) : ViewModel()  {

    val currentUser = repository.currentUser

    fun signInWithEmailAndPassword(email: String, password: String) {
        // 필요한 경우 추가 로직
        repository.signInWithEmailAndPassword(email, password)
    }
}