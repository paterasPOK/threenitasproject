package com.example.test.book.domain.model.model

import com.google.gson.annotations.SerializedName

data class Book(
    @SerializedName("id")val id: Int,
    @SerializedName("title")val title: String,
    @SerializedName("img_url") val imageUrl: String,
    @SerializedName("date_released") val dateReleased: String,
    @SerializedName("pdf_url") val pdfUrl: String,


)

