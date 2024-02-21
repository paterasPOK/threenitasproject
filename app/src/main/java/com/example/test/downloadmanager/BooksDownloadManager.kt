package com.example.test.downloadmanager

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
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

        val onComplete = object :BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                val id = intent?.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)
                if (id == downloadId){
                    val query = DownloadManager.Query().setFilterById(downloadId)
                    val cursor = downloadManager.query(query)

                    if (cursor.moveToFirst()) {
                        val columnIndex = cursor.getColumnIndex(DownloadManager.COLUMN_STATUS)
                        if (DownloadManager.STATUS_SUCCESSFUL == cursor.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI)){
                            book.isDownloaded = true
                            callback(BooksViewState(complete = true, progress = 100, isDownloaded = true, books = listOf(), error = null))
                        }
                    }
                }
                context?.unregisterReceiver(this)
            }


        }
        context.registerReceiver(onComplete, IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE))
    }
}