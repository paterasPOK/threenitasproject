package com.example.test.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import com.example.test.domain.model.model.LoginResponse
import com.example.test.domain.model.model.NetworkError
import com.example.test.domain.model.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: Repository
)
: ViewModel(){

    private val _loginResult = MutableStateFlow<Either<NetworkError, LoginResponse>?>(null)
    val loginResult: MutableStateFlow<Either<NetworkError, LoginResponse>?> get() = _loginResult

    fun userLogin(username: String, password: String) {
        viewModelScope.launch(Dispatchers.IO){
            val result = repository.userLogin(username, password)
            _loginResult.value = result
        }

    }

}