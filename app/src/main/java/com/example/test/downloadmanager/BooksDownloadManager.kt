package com.example.test.downloadmanager

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import com.example.test.book.domain.model.model.Book
import com.example.test.book.presentation.screens.BooksViewState
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class BooksDownloadManager @Inject constructor(
    @ApplicationContext private val context: Context
){
    fun downloadBook(book: Book, url: String, title :String, callback: (BooksViewState) -> Unit){
        val request = DownloadManager.Request(Uri.parse(url))
            .setTitle(title)
            .setDescription("Downloading $title")
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, title)

        val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        val downloadId = downloadManager.enqueue(request)
    }
}