package com.example.test.book.domain.model.repository

import arrow.core.Either
import com.example.test.book.domain.model.model.Book
import com.example.test.book.domain.model.model.LoginResponse
import com.example.test.book.domain.model.model.NetworkError
import com.example.test.book.presentation.screens.BookViewStates
import com.example.test.book.presentation.screens.BooksViewState
import javax.inject.Inject

interface Repository {

    suspend fun userLogin(
        username: String,
        password: String
    ): Either <NetworkError, LoginResponse>

    suspend fun getBooks(): Either <NetworkError, List<Book>>

    suspend fun downloadBook(
        book: Book,
        url: String,
        title: String,
        progressCallBack : (BooksViewState) -> Unit
    )


}