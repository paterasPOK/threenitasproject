package com.example.test.book.data.maper

import com.example.test.domain.model.model.ApiError
import com.example.test.domain.model.model.NetworkError
import java.io.IOException


fun Throwable.toNetworkError(): NetworkError {
    val error = when(this){
        is IOException -> ApiError.NetworkError
        is retrofit2.HttpException -> ApiError.UnknownError
        else -> ApiError.UnknownError
    }
    return NetworkError(
        error = error,
        t = this
    )
}