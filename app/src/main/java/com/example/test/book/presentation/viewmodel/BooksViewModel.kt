package com.example.test.book.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test.book.domain.model.model.Book
import com.example.test.book.domain.model.repository.Repository
import com.example.test.book.presentation.screens.BookViewStates
import com.example.test.book.presentation.screens.BooksViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BooksViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _state = MutableStateFlow(BooksViewState())
    val state = _state.asStateFlow()

    init {
        fetchBooks()
    }

    fun fetchBooks() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getBooks().map { books ->
                books.map { book ->
                    val isDownloaded = false
                    val progress = 0
                    val downloadIconVisible = true
                    BookViewStates(
                        book,
                        isDownloaded = isDownloaded,
                        progress = progress,
                        downloadIconVisible = downloadIconVisible
                    )
                }
            }.onRight { bookViewStates ->
                val books = bookViewStates.map { it.book }
                _state.update { currentState -> currentState.copy(books = books) }
            }
                .onLeft { error -> _state.update { it.copy(error = error.error.message) } }

        }
    }


    fun downloadBook(book: Book) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.downloadBook(book, book.pdfUrl, book.title) { downloadState ->
                _state.value = downloadState
            }
        }
    }
}