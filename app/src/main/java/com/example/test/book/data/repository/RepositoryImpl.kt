package com.example.test.book.data.repository

import android.app.DownloadManager
import arrow.core.Either
import com.example.test.book.data.api.Api
import com.example.test.book.data.maper.toNetworkError
import com.example.test.book.domain.model.model.Book
import com.example.test.book.domain.model.model.LoginRequest
import com.example.test.book.domain.model.model.LoginResponse
import com.example.test.book.domain.model.model.NetworkError
import com.example.test.book.domain.model.repository.Repository
import com.example.test.downloadmanager.BooksDownloadManager
import com.example.test.book.presentation.screens.BooksViewState
import com.example.test.tokenstoragemanager.TokenStorageManager
import java.io.IOException
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val api: Api,
    private val tokenStorageManager: TokenStorageManager,
    private val downloadManager: BooksDownloadManager
): Repository {

    override suspend fun userLogin(username: String,password: String): Either<NetworkError, LoginResponse> {
        val loginRequest = LoginRequest(username, password)
        return Either.catch {
            val response = api.userLogin(loginRequest)
            val token = response.access_token
            token.let { tokenStorageManager.saveToken(it) }
            response
        }.mapLeft { it.toNetworkError() }
    }

    override suspend fun getBooks(): Either<NetworkError, List<Book>> {
        return Either.catch {
            val token = tokenStorageManager.getToken()
            token?.let {
                api.getBooks(it)
            }?: throw IOException("Token Not Found")

        }.mapLeft { it.toNetworkError() }
    }

    override suspend fun downloadBook(
        book: Book,
        url: String,
        title: String,
        progressCallBack: (BooksViewState) -> Unit
    ) {
        downloadManager.downloadBook(book, url, title, progressCallBack)
    }
}