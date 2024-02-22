package com.example.test.book.presentation.screens

import com.example.test.book.domain.model.model.Book

data class BooksViewState (
    val books: List<Book> = emptyList(),
    val error: String? = null
)

data class BookViewStates(
    val book:Book,
    val progress: Int = 0,
    val isDownloaded: Boolean = false,
    val downloadIconVisible: Boolean = false,
)
// This class build for live progress of the download or to check if the book is downloaded, because the Book class is made only for the data that the api returns