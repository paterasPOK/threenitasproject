package com.example.test.book.presentation.viewmodel

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Environment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import com.example.test.book.domain.model.model.Book
import com.example.test.book.domain.model.model.NetworkError
import com.example.test.book.domain.model.repository.Repository
import com.example.test.book.presentation.screens.BooksViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class BooksViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {

    private val _state = MutableStateFlow(BooksViewState())
    val state = _state.asStateFlow()
    private val _booksState = MutableStateFlow<List<Book>>(emptyList())
    val booksState = _booksState.asStateFlow()


    init {
        fetchBooks()
    }

    fun fetchBooks() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getBooks()
                .onRight { books -> _state.update { it.copy(books = books)} }
                .onLeft { error -> _state.update { it.copy(error = error.error.message)} }

        }
    }

    fun downloadBook(book: Book) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.downloadBook(book,book.pdfUrl, book.title) {downloadState ->
                _state.value = downloadState
                val updatedBooks = downloadState.books.map { if (it.id == book.id) book.copy(isDownloaded = true) else it }
                _booksState.value = updatedBooks
            }
        }
    }

    //fun openBook(book: Book) {
        //if(bookIsDownloaded(book)) {
            //openDownloadedBook(book)
        //}
    //}

    //private fun bookIsDownloaded(book: Book) : Boolean {
        //val filePath = getBookFilePath(book)
        //return filePath != null && File(filePath)
    //}

    //private fun getBookFilePath(book: Book) : String? {
        //val downloadsDirectory = context
    //}


}