package com.example.test.domain.model.repository

import arrow.core.Either
import com.example.test.domain.model.model.LoginResponse
import com.example.test.domain.model.model.NetworkError

interface Repository {

    suspend fun userLogin(
        username: String,
        password: String
    ): Either <NetworkError, LoginResponse>

    //suspend fun getBooks(): Either <NetworkError, List<Book>>
}