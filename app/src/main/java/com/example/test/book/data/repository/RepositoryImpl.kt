package com.example.test.book.data.repository

import arrow.core.Either
import com.example.test.book.data.api.Api
import com.example.test.book.data.maper.toNetworkError
import com.example.test.domain.model.model.LoginRequest
import com.example.test.domain.model.model.LoginResponse
import com.example.test.domain.model.model.NetworkError
import com.example.test.domain.model.repository.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val api: Api
): Repository {

    override suspend fun userLogin(username: String,password: String): Either<NetworkError, LoginResponse> {
        val loginRequest = LoginRequest(username, password)
        return Either.catch {
            api.userLogin(loginRequest)
        }.mapLeft { it.toNetworkError() }
    }
}