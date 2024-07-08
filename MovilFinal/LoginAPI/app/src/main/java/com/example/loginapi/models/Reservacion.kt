package com.example.loginapi.models

data class Reservacion( val id: Int,
                        val restaurantId: Int,
                        val date: String,
                        val time: String,
                        val people: Int,
                        val food: List<Food>?
                        )
