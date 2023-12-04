package com.example.ootdfinalapplication.viewmodel

import android.app.Application
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.ootdfinalapplication.DetailViewAdapter
import com.example.ootdfinalapplication.R
import com.example.ootdfinalapplication.UserImageInfo
import com.example.ootdfinalapplication.repository.Add2Repository
import com.google.firebase.auth.FirebaseUser



class EntryViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = Add2Repository()
    val currentUser: LiveData<FirebaseUser?> = repository.currentUser

    fun signInWithEmailAndPassword(email: String, password: String) {
        repository.signInWithEmailAndPassword(email, password)
    }
}
