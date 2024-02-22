package com.example.test.book.presentation.screens.components

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import com.example.test.ui.theme.bgColor
import com.example.test.ui.theme.letterColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopBar (
    title: String
){
    CenterAlignedTopAppBar(
        title = { Text(text = title) },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = bgColor, titleContentColor = letterColor)
    )


}