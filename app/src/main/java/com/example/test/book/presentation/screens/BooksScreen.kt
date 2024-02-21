package com.example.test.book.presentation.screens

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.test.book.presentation.screens.components.BooksContent
import com.example.test.book.presentation.viewmodel.BooksViewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BooksScreen(
    navController: NavController,
    booksViewModel: BooksViewModel = hiltViewModel()
) {
    val state by booksViewModel.state.collectAsStateWithLifecycle()
    BooksContent(state = state, booksViewModel = booksViewModel, navController = navController)
}


