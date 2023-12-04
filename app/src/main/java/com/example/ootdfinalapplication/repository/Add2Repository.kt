package com.example.ootdfinalapplication.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth

class Add2Repository {


    val auth: FirebaseAuth = FirebaseAuth.getInstance()
    val _currentUser = MutableLiveData<FirebaseUser?>()

    val currentUser: LiveData<FirebaseUser?> get() = _currentUser

    fun saveUserInfo(user: FirebaseUser?) {
        _currentUser.value = user
    }

    fun signInWithEmailAndPassword(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    saveUserInfo(user)
                } else {
                }
            }
    }
}
