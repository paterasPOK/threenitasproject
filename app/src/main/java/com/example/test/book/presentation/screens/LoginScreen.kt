package com.example.test.book.presentation.screens

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.test.book.presentation.screens.components.LoginContent
import com.example.test.book.presentation.viewmodel.LoginViewModel


@Composable
fun LoginScreen(
    navController: NavController,
    loginViewModel: LoginViewModel = hiltViewModel()
) {
    LoginContent(navController, loginViewModel)
}





