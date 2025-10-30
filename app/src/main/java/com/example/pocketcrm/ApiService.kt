package com.example.pocketcrm

import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("Notes/{userId}")
    suspend fun getNotes(@Path("userId") userId: String): NotesResponse
}