package com.example.test.book.domain.model.model

import com.google.gson.annotations.SerializedName

data class Book(
    val id: Int,
    val title: String,
    @SerializedName("img_url") val imageUrl: String,
    @SerializedName("date_released") val dateReleased: String,
    @SerializedName("pdf_url") val pdfUrl: String,
    var isDownloaded: Boolean = false,
    var isDownloading: Boolean = false,
    var showDownloadIcon: Boolean = false,
    var downloadProgress: Int = 0
)
