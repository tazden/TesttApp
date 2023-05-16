package com.example.testapp.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UnsplashApi {
    @GET("photos/")
    fun getPhotos(@Query("client_id") apiKey: String): Call<List<Photo>>

    @GET("photos/{id}/")
    fun getPhotoDetails(
        @Path("id") photoId: String,
        @Query("client_id") apiKey: String
    ): Call<PhotoDetails>
}
