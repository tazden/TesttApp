package com.example.testapp.repository

import com.example.testapp.model.ApiClient
import com.example.testapp.model.Photo
import com.example.testapp.model.PhotoDetails
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PhotoRepository {
    private val unsplashApi = ApiClient.unsplashApi

    fun getPhotos(callback: (List<Photo>?) -> Unit) {
        val call = unsplashApi.getPhotos(ApiClient.API_KEY)
        call.enqueue(object : Callback<List<Photo>> {
            override fun onResponse(call: Call<List<Photo>>, response: Response<List<Photo>>) {
                if (response.isSuccessful) {
                    val photos = response.body()
                    callback(photos)
                } else {
                    callback(null)
                }
            }

            override fun onFailure(call: Call<List<Photo>>, t: Throwable) {
                callback(null)
            }
        })
    }

    fun getPhotoDetails(photoId: String, callback: (PhotoDetails?) -> Unit) {
        val call = unsplashApi.getPhotoDetails(photoId, ApiClient.API_KEY)
        call.enqueue(object : Callback<PhotoDetails> {
            override fun onResponse(call: Call<PhotoDetails>, response: Response<PhotoDetails>) {
                if (response.isSuccessful) {
                    val photoDetails = response.body()
                    callback(photoDetails)
                } else {
                    callback(null)
                }
            }

            override fun onFailure(call: Call<PhotoDetails>, t: Throwable) {
                callback(null)
            }
        })
    }
}
