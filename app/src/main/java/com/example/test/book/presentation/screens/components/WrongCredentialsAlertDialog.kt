package com.example.test.book.presentation.screens.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.example.test.ui.theme.alertDialogButton
import com.example.test.ui.theme.bgColor
import com.example.test.ui.theme.dimens
import com.example.test.ui.theme.green
import com.example.test.ui.theme.letterColor
import com.example.test.util.Constant.ALERT_DIALOG_BTN_TEXT
import com.example.test.util.Constant.ALERT_DIALOG_TEXT
import com.example.test.util.Constant.ALERT_DIALOG_TITLE

@Composable
fun CredentialsAlertDialog(
    showDialog: MutableState<Boolean>
) {
    if (showDialog.value) {
        AlertDialog(
            containerColor = bgColor,
            onDismissRequest = {},
            text = {
                Column(
                    modifier = Modifier.padding(MaterialTheme.dimens.small2),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = ALERT_DIALOG_TITLE,
                        style = MaterialTheme.typography
                            .bodyLarge.copy(letterColor) )
                    Text(text = ALERT_DIALOG_TEXT,
                        style = MaterialTheme.typography.
                        bodyMedium.copy(letterColor))

                }
            },
            confirmButton = {
                Button(
                    onClick = { showDialog.value = false },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(alertDialogButton)) {
                    Text(text = ALERT_DIALOG_BTN_TEXT, color = green)
                }

            })
    }

}
