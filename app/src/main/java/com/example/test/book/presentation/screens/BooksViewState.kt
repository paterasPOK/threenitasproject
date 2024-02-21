package com.example.test.book.presentation.screens

import com.example.test.book.domain.model.model.Book

data class BooksViewState (
    val isDownloaded: Boolean = false,
    val progress: Int = 0,
    val complete: Boolean = false,
    val downloadIconVisible: Boolean = false,
    val books: List<Book> = emptyList(),
    val error: String? = null
)
