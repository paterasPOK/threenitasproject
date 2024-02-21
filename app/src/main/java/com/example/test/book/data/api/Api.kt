package com.example.test.book.data.api

import com.example.test.book.domain.model.model.Book
import com.example.test.book.domain.model.model.LoginRequest
import com.example.test.book.domain.model.model.LoginResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST


interface Api {

    @POST("login")
    suspend fun userLogin(
        @Body loginRequest: LoginRequest
    ): LoginResponse

    @GET("books")
    suspend fun getBooks(@Header("Authorization") token: String): List<Book>

}

