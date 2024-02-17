package com.example.test.screens

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.test.R
import com.example.test.ui.theme.green
import com.example.test.ui.theme.letterColor



@Composable
fun LoginScreen(navController: NavHostController) {

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val showPassword by remember { mutableStateOf(false) }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .fillMaxHeight()
            .background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Spacer(modifier = Modifier.height(23.dp))

        Text(text = "Σύνδεση", fontSize = 24.sp, color = letterColor)

        Spacer(modifier = Modifier.height(150.dp))

        Column {
            UserIdField(username){
                username = it
            }
        }

        Spacer(modifier = Modifier.height(19.dp))

        Column {
            PasswordField(showPassword, password) {
                password = it
            }
        }

        Spacer(modifier = Modifier.height(19.dp))

        SubmitButton(username, password)

    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun UserIdField(
    username: String,
    onUserIdChange: (String) -> Unit
) {
    var username1 = username

    Row(
        modifier = Modifier.fillMaxWidth(0.5f),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Text(text = "UserID", fontSize = 16.sp, color = letterColor)

        IconButton(
            onClick = {

            }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_info_24),
                contentDescription = null,
                tint = green
            )
        }
    }

    TextField(
        value = username1,
        onValueChange = {
            username1 = it
            onUserIdChange(it)},
        singleLine = true,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = letterColor,
            cursorColor = letterColor,
            focusedBorderColor = green,
            unfocusedBorderColor = green
        )
    )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun PasswordField(showPassword: Boolean, password: String, onPasswordChange: (String) -> Unit) {

    var showPassword1 by remember { mutableStateOf(showPassword) }

    var password1 = password
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "Κωδικός", fontSize = 16.sp, color = letterColor)

        IconButton(
            onClick = { /*TODO*/ }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_info_24),
                contentDescription = null,
                tint = green
            )
        }

        Button(
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black
            ),
            onClick = {
                showPassword1 = !showPassword1

            }) {

            if (showPassword1) {
                Text(text = "Κρύψιμο", color = green)
            } else
                Text(text = "Προβολή", color = green)
        }

    }

    TextField(
        value = password1,
        onValueChange = {
            password1 = it
            onPasswordChange(it)},
        singleLine = true,
        visualTransformation = if (showPassword1) {
            VisualTransformation.None

        } else {
            PasswordVisualTransformation()
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = letterColor,
            cursorColor = letterColor,
            focusedBorderColor = green,
            unfocusedBorderColor = green

        )
    )
}



@Composable
private fun SubmitButton(username: String, password: String) {
    val mContext = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(47.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom

    ) {
        OutlinedButton(
            onClick = {
                val isValid = CheckCredentials(username.trim(), password.trim())

                if (!isValid) {
                    Toast.makeText(mContext, "Invalid UserId or Password", Toast.LENGTH_LONG).show()
                } else
                    Toast.makeText(mContext, "Valid UserId or Password", Toast.LENGTH_LONG).show()


            },
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = green
            ),
            border = BorderStroke(1.dp, green),
            modifier = Modifier.fillMaxWidth()

        ) {

            Text("Σύνδεση") // Set the text for the button
        }


    }
}


fun CheckCredentials(username: String, password: String) : Boolean {

    val userIdRegex = Regex("^[A-Z]{2}\\d{4}$")
    val passwordRegex = Regex("^(?=.*[A-Z].*[A-Z])(?=.*[!@#\$%^&*()])(?=.*\\d.*\\d)(?=.*[a-z].*[a-z].*[a-z]).{8,}$")



    val usernameValid = username.matches(userIdRegex)
    val passwordValid = password.matches(passwordRegex)
    return usernameValid && passwordValid
}

@Preview
@Composable
fun PreviewLoginScreen() {
    val navController = rememberNavController()
    LoginScreen(navController)
}





