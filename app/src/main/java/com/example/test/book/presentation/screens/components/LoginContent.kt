package com.example.test.book.presentation.screens.components

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import arrow.core.Either
import com.example.test.R
import com.example.test.book.presentation.viewmodel.LoginViewModel
import com.example.test.ui.theme.ScreenOrientation
import com.example.test.ui.theme.dimens
import com.example.test.ui.theme.green
import com.example.test.ui.theme.letterColor
import com.example.test.util.Constant
import com.example.test.util.Constant.HIDE_BTN_TEXT
import com.example.test.util.Constant.PASSWORD_FIELD_HEADER
import com.example.test.util.Constant.PASSWORD_INFO
import com.example.test.util.Constant.PASSWORD_REGEX_PATTERN
import com.example.test.util.Constant.SHOW_BTN_TEXT
import com.example.test.util.Constant.SUBMIT_BTN_TEXT
import com.example.test.util.Constant.USERID_FIELD_HEADER
import com.example.test.util.Constant.USERID_REGEX_PATTERN
import com.example.test.util.Constant.USER_ID_INFO

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginContent(
    navController: NavController,
    loginViewModel: LoginViewModel
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val showPassword by remember { mutableStateOf(false) }
    val showAlertDialog = remember { mutableStateOf(false) }

    Scaffold(
        topBar = { MyTopBar(title = Constant.LOGIN_SCREEN_HEADER_TEXT) },
        containerColor = Color.DarkGray
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .statusBarsPadding(),
            contentAlignment = Alignment.Center
        ) {

            if (ScreenOrientation == Configuration.ORIENTATION_PORTRAIT) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Column {
                        UserIdField(username) {
                            username = it
                        }
                    }

                    Spacer(modifier = Modifier.height(MaterialTheme.dimens.small3))

                    Column {
                        PasswordField(showPassword, password) {
                            password = it
                        }
                    }

                    Spacer(modifier = Modifier.height(MaterialTheme.dimens.small3))

                    SubmitButton(username, password, navController, loginViewModel, showAlertDialog)
                }
            } else {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Column {
                        UserIdField(username) {
                            username = it
                        }
                    }

                    Spacer(modifier = Modifier.height(MaterialTheme.dimens.small1))

                    Column {
                        PasswordField(showPassword, password) {
                            password = it
                        }
                    }

                    Spacer(modifier = Modifier.height(MaterialTheme.dimens.small1))

                    SubmitButton(username, password, navController, loginViewModel, showAlertDialog)
                }
            }


        }
    }

}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun UserIdField(
    username: String,
    onUserIdChange: (String) -> Unit
) {
    var username1 = username
    val showDialog = remember { mutableStateOf(false) }

    Row(
        modifier = Modifier.fillMaxWidth(0.5f),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Text(
            text = USERID_FIELD_HEADER,
            style = MaterialTheme.typography.titleLarge.copy(letterColor)
        )

        IconButton(
            onClick = {
                showDialog.value = true
            }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_info_24),
                contentDescription = null,
                tint = green
            )
        }
    }
    if (showDialog.value) {
        CredentialsInfo(onDismiss = { showDialog.value = false }, infoText = USER_ID_INFO)

    }

    TextField(
        value = username1,
        onValueChange = {
            username1 = it
            onUserIdChange(it)
        },
        singleLine = true,
        textStyle = MaterialTheme.typography.titleMedium,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedTextColor = letterColor,
            unfocusedTextColor = letterColor,
            cursorColor = letterColor,
            focusedBorderColor = green,
            unfocusedBorderColor = green
        )
    )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun PasswordField(
    showPassword: Boolean,
    password: String,
    onPasswordChange: (String) -> Unit
) {

    var showPassword1 by remember { mutableStateOf(showPassword) }
    val showDialog = remember { mutableStateOf(false) }
    var password1 = password

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Absolute.Center
    ) {
        Text(
            text = PASSWORD_FIELD_HEADER,
            style = MaterialTheme.typography.titleLarge.copy(letterColor)
        )

        IconButton(
            onClick = { showDialog.value = true }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_info_24),
                contentDescription = null,
                tint = green
            )
        }
        if (showDialog.value) {
            CredentialsInfo(onDismiss = { showDialog.value = false }, PASSWORD_INFO)
        }

        Button(
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black
            ),
            onClick = { showPassword1 = !showPassword1 }) {
            if (showPassword1) {
                Text(text = HIDE_BTN_TEXT, color = green)
            } else
                Text(text = SHOW_BTN_TEXT, color = green)
        }

    }

    TextField(
        value = password1,
        onValueChange = {
            password1 = it
            onPasswordChange(it)
        },
        singleLine = true,
        visualTransformation = if (showPassword1) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        textStyle = MaterialTheme.typography.titleMedium,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedTextColor = letterColor,
            focusedTextColor = letterColor,
            cursorColor = letterColor,
            focusedBorderColor = green,
            unfocusedBorderColor = green

        )
    )
}


@Composable
private fun SubmitButton(
    username: String,
    password: String,
    navController: NavController,
    loginViewModel: LoginViewModel,
    showAlertDialog: MutableState<Boolean>
) {
    val mContext = LocalContext.current
    val loginResult by loginViewModel.loginResult.collectAsState()

    OutlinedButton(
        onClick = {
            val isValid = checkCredentials(username.trim(), password.trim())

            if (!isValid) { // Show the Wrong credentials popup
                Toast.makeText(
                    mContext,
                    "Invalid UserId or Password",
                    Toast.LENGTH_LONG
                ).show()
            } else { // Make Connection Attempt
                loginViewModel.userLogin(username, password)
            }
        },
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = green
        ),
        border = BorderStroke(1.dp, green),

        ) {
        Text(
            SUBMIT_BTN_TEXT,
            style = MaterialTheme.typography.bodyLarge.copy(green)
        )
    }

    LaunchedEffect(loginResult) {
        loginResult?.let {
            when (it) {
                is Either.Left -> {// Left means error, so change the value of showAlertDialog in order to inform the User
                    showAlertDialog.value = true
                }

                is Either.Right -> navController.navigate("books")
            }
        }
    }
    if (showAlertDialog.value) {
        CredentialsAlertDialog(showDialog = showAlertDialog)
    }
}


fun checkCredentials(username: String, password: String): Boolean {
    val userIdRegex = Regex(USERID_REGEX_PATTERN)
    val passwordRegex = Regex(PASSWORD_REGEX_PATTERN)

    val usernameValid = username.matches(userIdRegex)
    val passwordValid = password.matches(passwordRegex)

    return usernameValid && passwordValid
}