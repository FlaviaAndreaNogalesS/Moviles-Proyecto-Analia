package com.example.movilesfinalrestaurante.models.response

import com.example.movilesfinalrestaurante.models.Restaurant

data class RestaurantResponse(
    val success: Boolean,
    val data: List<Restaurant>
)