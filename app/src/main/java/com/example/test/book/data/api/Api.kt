package com.example.test.book.data.api

import com.example.test.domain.model.model.LoginRequest
import com.example.test.domain.model.model.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST


interface Api {

    @POST("login")
    suspend fun userLogin(
        @Body loginRequest: LoginRequest
    ): LoginResponse

    //@GET

}

