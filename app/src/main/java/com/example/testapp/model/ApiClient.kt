package com.example.testapp.model

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val BASE_URL = "https://api.unsplash.com/"
    const val API_KEY = "_NbyEWYypdRsMndpUW-8JPIAlD1gk0fzG3jDLQrgtqM"

    val retrofit: Retrofit by lazy {
        val client = OkHttpClient.Builder().build()
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }
    val unsplashApi: UnsplashApi by lazy {
        retrofit.create(UnsplashApi::class.java)
    }
}
