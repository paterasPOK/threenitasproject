package com.example.test.tokenstoragemanager

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class TokenStorageManager @Inject constructor(@ApplicationContext context: Context){

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("token_prefs", Context.MODE_PRIVATE)
    fun saveToken(token: String) {
        sharedPreferences.edit().putString("access_token", token).apply()
    }

    fun getToken(): String? {
        return sharedPreferences.getString("access_token", null)
    }
}