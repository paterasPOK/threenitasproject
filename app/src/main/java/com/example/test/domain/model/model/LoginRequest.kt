package com.example.test.domain.model.model

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("UserName") val username: String,
    @SerializedName("Password") val password: String
)
