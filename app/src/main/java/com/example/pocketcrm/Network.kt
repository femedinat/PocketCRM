package com.example.pocketcrm

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Network {
    private const val BASE_URL = "https://fiapchallengewtc-cdhqe6hfdqe0aghv.brazilsouth-01.azurewebsites.net/"

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService: ApiService = retrofit.create(ApiService::class.java)
}