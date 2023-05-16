package com.example.testapp.model

data class Photo(
    val id: String,
    val urls: PhotoUrls
)

data class PhotoUrls(
    val regular: String
)

data class PhotoDetails(
    val id: String,
    val description: String?,
    val likes: Int
)