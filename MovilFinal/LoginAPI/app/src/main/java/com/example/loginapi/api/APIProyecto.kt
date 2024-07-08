package com.example.loginapi.api

import com.example.loginapi.models.RegisterRequestDTO
import com.example.loginapi.models.Reservacion
import com.example.loginapi.models.Restaurant
import com.example.loginapi.models.dto.LoginRequestDTO
import com.example.loginapi.models.dto.LoginResponseDTO
import com.example.loginapi.models.dto.RegisterResponseDTO
import com.example.loginapi.models.dto.ReservationRequest
import com.example.loginapi.models.dto.RestaurantRequest
import com.example.loginapi.models.dto.SearchRequestDTO
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface APIProyecto {
    ///iniciar secion
    @POST("loginuser")
    fun login(@Body loginRequest: LoginRequestDTO): Call<LoginResponseDTO>

    ///pedir la lista de restaurantes en ves de get es post
    @POST("restaurants/search")
    fun searchRestaurants(@Body searchRequest: SearchRequestDTO): Call<List<Restaurant>>
    @GET("restaurants/{id}")
    fun getRestaurantById(@Path("id") id: Int): Call<Restaurant>

    //El token se tiene que mandar como Bearer + El string que se obtiene de PreferencesRepository.getToken()
    @POST("restaurants")
    fun insertRestaurant(@Header("Authorization") token: String, @Body restaurant: Restaurant): Call<Restaurant>
    @POST("registeruser")
    fun registerUser(@Body registerRequest: RegisterRequestDTO): Call<RegisterResponseDTO>

    @POST("/api/reservations")
    fun makeReservation(@Body reservationRequest: ReservationRequest): Call<Void>

    @GET("reservations")
    fun getReservations(@Header("Authorization") token: String): Call<List<Reservacion>>

    @POST("/api/restaurants")
    fun createRestaurant(@Header("Authorization") token: String, @Body restaurantRequest: RestaurantRequest): Call<Void>

}