package com.example.loginapi.models

data class Restaurant(val id: Int,
                      val name: String,
                      val address: String,
                      val city: String,
                      val description: String,
                      val user_id: Int,
                      val logo: String)
