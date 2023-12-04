package com.example.ootdfinalapplication.Model

data class ContentModel(
    var uid: String? = null,
    var explain : String? = null,
    var imageUrl : String? = null,
    var userId: String? = null,
    var timestamp: Long? = null,
    var favoriteCount: Int = 0,
    var favorites : MutableMap<String,Boolean> = HashMap()

)
