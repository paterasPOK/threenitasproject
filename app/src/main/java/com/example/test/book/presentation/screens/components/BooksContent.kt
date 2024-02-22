package com.example.test.book.presentation.screens.components

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.test.book.presentation.screens.BooksViewState
import com.example.test.book.presentation.viewmodel.BooksViewModel
import com.example.test.ui.theme.dimens
import com.example.test.util.Constant.BOOKS_SCREEN_HEADER_TEXT

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun BooksContent(
    state: BooksViewState,
    booksViewModel: BooksViewModel,
    navController: NavController
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { MyTopBar(title = BOOKS_SCREEN_HEADER_TEXT) },
        bottomBar = {
            MyNavigationBar(navController = navController)
        },
        containerColor = Color.Black
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            val sortedBooks = state.books.sortedWith(compareByDescending { it.dateReleased })

            LazyVerticalGrid(
                modifier = Modifier.padding(
                    top = it.calculateTopPadding(),
                    bottom = it.calculateTopPadding()
                ),
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(MaterialTheme.dimens.small1)
            ) {

                items(sortedBooks) { book ->
                    BookCard(
                        book = book,
                        booksViewModel = booksViewModel
                    )
                }

            }
        }

    }
}

