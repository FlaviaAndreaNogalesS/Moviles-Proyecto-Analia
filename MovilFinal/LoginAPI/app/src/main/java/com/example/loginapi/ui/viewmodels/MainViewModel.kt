package com.example.loginapi.ui.viewmodels

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.loginapi.api.APIProyecto
import com.example.loginapi.models.dto.LoginRequestDTO
import com.example.loginapi.repositories.PreferencesRepository
import com.example.loginapi.repositories.UserRepository
import com.example.loginapi.ui.activities.RestaurantListActivity
import com.example.loginapi.ui.activities.UserAccessActivity

class MainViewModel : ViewModel() {

    private val _errorMessage: MutableLiveData<String> by lazy {
        MutableLiveData<String>("")
    }
    val errorMessage: LiveData<String> get() = _errorMessage
    fun login(email: String, password: String, context: Context) {
        UserRepository.doLogin(email,
            password,
            success = {
                if(it == null) {
                    _errorMessage.value = "Usuario o contraseña incorrectos"
                    return@doLogin
                }
                _errorMessage.value = ""
                Log.d("MainViewModel", "Token: ${it.access_token}")
                val token: String = it.access_token!!
                PreferencesRepository.saveToken(token, context)
                var intent = Intent(context, UserAccessActivity::class.java)
                context.startActivity(intent)

            }, failure = {
                it.printStackTrace()

            })
    }
}