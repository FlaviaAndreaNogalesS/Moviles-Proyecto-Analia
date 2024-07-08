package com.example.loginapi.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.loginapi.models.dto.RegisterResponseDTO
import com.example.loginapi.repositories.UserRepository

class RegisterViewModel : ViewModel(){
  val registerResult = MutableLiveData<RegisterResponseDTO?>()
  val error = MutableLiveData<Throwable>()
    fun registerUser(name: String, email: String, password: String, phone: String) {
      UserRepository.doRegister(name, email, password, phone, {
        registerResult.value = it
      }, {
        error.value = it
      })

    }
}

