package com.example.test.book.presentation.screens.components

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.example.test.book.presentation.screens.BooksViewState
import com.example.test.book.presentation.viewmodel.BooksViewModel
import com.example.test.util.Constant.BOOKS_SCREEN_HEADER_TEXT

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun BooksContent(
    state: BooksViewState,
    booksViewModel: BooksViewModel,
    navController: NavController
) {

    var currentYear by remember { mutableStateOf("2020") }
    var nextYear by remember { mutableStateOf(currentYear) }



    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { MyTopBar(title = BOOKS_SCREEN_HEADER_TEXT) },
        bottomBar = {
            MyNavigationBar(navController = navController) },
        containerColor = Color.DarkGray
    ) {
        val sb = state.books.sortedWith(compareByDescending { it.dateReleased })
        LazyColumn {
            stickyHeader {
                val yearHeader = Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .zIndex(1f)
                ) {
                    if (nextYear != currentYear) {
                        currentYear = nextYear
                    }
                    Text(text = currentYear, color = Color.Black, fontSize = 20.sp)
                }
                yearHeader
            }
        }

        LazyVerticalGrid(
            modifier = Modifier.padding(
                top = it.calculateTopPadding(),
                bottom = it.calculateTopPadding()
            ),
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            items(sb) { book ->
                val bookYear = book.dateReleased
                nextYear = bookYear
                BookCard(
                    book = book,
                    booksViewModel = booksViewModel
                )
            }

        }

    }
}