package com.example.test.book.presentation.screens.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.test.R
import com.example.test.book.domain.model.model.Book
import com.example.test.book.presentation.viewmodel.BooksViewModel

@Composable
fun BookCard(
    modifier: Modifier = Modifier,
    book: Book,
    booksViewModel: BooksViewModel
) {
    Log.d("BookCard", "Download Progress: ${book.downloadProgress}")
    Card(
        modifier = modifier
            .padding(8.dp)
            .clickable { booksViewModel.downloadBook(book) },
        colors = CardDefaults.cardColors(containerColor = Color.Black)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            AsyncImage(
                model = book.imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f),
                contentScale = ContentScale.FillBounds
            )
            if (book.downloadProgress > 0f) {
                LinearProgressIndicator(
                    progress = book.downloadProgress / 100f,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    color = Color.Blue
                )
            }
            Text(text = book.dateReleased)
            Spacer(modifier = Modifier.height(5.dp))

            Text(text = book.title, color = Color.White, fontSize = 16.sp)

        }

    }
}