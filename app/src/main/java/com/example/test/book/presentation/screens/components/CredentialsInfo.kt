package com.example.test.book.presentation.screens.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.test.ui.theme.bgColor
import com.example.test.ui.theme.letterColor


@Composable
fun CredentialsInfo(onDismiss: () -> Unit, infoText: String) {
    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = MaterialTheme.shapes.medium,
            color = bgColor
        ) {
            Column(modifier = Modifier.padding(all = 16.dp)) {
                Text(
                    text = infoText,
                    color = letterColor
                )
            }
        }
    }
}
