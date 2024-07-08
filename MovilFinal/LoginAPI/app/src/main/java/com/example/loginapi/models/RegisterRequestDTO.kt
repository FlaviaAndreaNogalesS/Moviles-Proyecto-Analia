package com.example.loginapi.models

data class RegisterRequestDTO(val name: String,
                              val email: String,
                              val password: String,
                              val phone: String)
