package com.example.test.book.presentation.screens.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import com.example.test.book.domain.model.model.Book
import com.example.test.book.presentation.viewmodel.BooksViewModel
import com.example.test.ui.theme.dimens
import com.example.test.util.Constant.BOOK_IMG_URL

@Composable
fun BookCard(
    modifier: Modifier = Modifier,
    book: Book,
    booksViewModel: BooksViewModel
) {
    Card(
        modifier = modifier
            .padding(MaterialTheme.dimens.small1)
            .clickable { booksViewModel.downloadBook(book) },
        colors = CardDefaults.cardColors(containerColor = Color.Black)
    ) {
        Column(
            modifier = Modifier.padding(MaterialTheme.dimens.small2)
        ) {
            AsyncImage(
                model = BOOK_IMG_URL,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f),
                contentScale = ContentScale.FillBounds
            )

            Spacer(modifier = Modifier.height(MaterialTheme.dimens.small1))

            Text(
                text = book.title,
                color = Color.White,
                fontSize = MaterialTheme.typography.titleMedium.fontSize
            )


        }

    }
}