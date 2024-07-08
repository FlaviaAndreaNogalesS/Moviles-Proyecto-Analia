package com.example.loginapi.repositories

import com.example.loginapi.api.APIProyecto
import com.example.loginapi.models.RegisterRequestDTO
import com.example.loginapi.models.Reservacion
import com.example.loginapi.models.Restaurant
import com.example.loginapi.models.dto.LoginRequestDTO
import com.example.loginapi.models.dto.LoginResponseDTO
import com.example.loginapi.models.dto.RegisterResponseDTO
import com.example.loginapi.models.dto.SearchRequestDTO
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object UserRepository {
    private val retrofit = RetrofitRepository.getRetrofitInstance()
    private val servicio: APIProyecto = retrofit.create(APIProyecto::class.java)
    fun doLogin(
        email: String,
        password: String,
        success: (LoginResponseDTO?) -> Unit,
        failure: (Throwable) -> Unit
    ) {
        val retrofit = RetrofitRepository.getRetrofitInstance()

        val service: APIProyecto =
            retrofit.create(APIProyecto::class.java)
        service.login(LoginRequestDTO(email, password))
            .enqueue(object : Callback<LoginResponseDTO> {
                override fun onResponse(
                    res: Call<LoginResponseDTO>,
                    response: Response<LoginResponseDTO>
                ) {
                    if (response.code() == 401) {
                        success(null)
                        return
                    }
                    val loginResponse = response.body()
                    success(loginResponse)
                }

                override fun onFailure(res: Call<LoginResponseDTO>, t: Throwable) {
                    failure(t)
                }
            })
    }

    // Método para registrar
    fun doRegister(
        name: String,
        email: String,
        password: String,
        phone: String,
        success: (RegisterResponseDTO?) -> Unit,
        failure: (Throwable) -> Unit
    ) {
        val retrofit = RetrofitRepository.getRetrofitInstance()
        val service: APIProyecto = retrofit.create(APIProyecto::class.java)
        service.registerUser(RegisterRequestDTO(name, email, password, phone)).enqueue(object : Callback<RegisterResponseDTO> {
            override fun onResponse(res: Call<RegisterResponseDTO>, response: Response<RegisterResponseDTO>) {
                if (response.isSuccessful) {
                    success(response.body())
                } else {
                    success(null)
                }
            }

            override fun onFailure(res: Call<RegisterResponseDTO>, t: Throwable) {
                failure(t)
            }
        })
    }


    fun buscarRestaurantes(
        searchRequest: SearchRequestDTO,
        exito: (List<Restaurant>?) -> Unit,
        fallo: (Throwable) -> Unit
    ) {
        servicio.searchRestaurants(searchRequest).enqueue(object : Callback<List<Restaurant>> {
            override fun onResponse(call: Call<List<Restaurant>>, response: Response<List<Restaurant>>) {
                if (response.isSuccessful) {
                    exito(response.body())
                } else {
                    exito(null)
                }
            }

            override fun onFailure(call: Call<List<Restaurant>>, t: Throwable) {
                fallo(t)
            }
        })
    }

    fun obtenerRestaurantePorId(
        id: Int,
        exito: (Restaurant?) -> Unit,
        fallo: (Throwable) -> Unit
    ) {
        servicio.getRestaurantById(id).enqueue(object : Callback<Restaurant> {
            override fun onResponse(call: Call<Restaurant>, response: Response<Restaurant>) {
                if (response.isSuccessful) {
                    exito(response.body())
                } else {
                    exito(null)
                }
            }

            override fun onFailure(call: Call<Restaurant>, t: Throwable) {
                fallo(t)
            }
        })
    }
    fun obtenerReservas(token: String, success: (List<Reservacion>?) -> Unit, failure: (Throwable) -> Unit) {
        servicio.getReservations("Bearer $token").enqueue(object : Callback<List<Reservacion>> {
            override fun onResponse(call: Call<List<Reservacion>>, response: Response<List<Reservacion>>) {
                if (response.isSuccessful) {
                    success(response.body())
                } else {
                    success(null)
                }
            }

            override fun onFailure(call: Call<List<Reservacion>>, t: Throwable) {
                failure(t)
            }
        })
    }
    fun  getReservations(token: String, success: (List<Reservacion>?) -> Unit, failure: (Throwable) -> Unit) {
        servicio.getReservations("Bearer $token").enqueue(object : Callback<List<Reservacion>> {
            override fun onResponse(call: Call<List<Reservacion>>, response: Response<List<Reservacion>>) {
                if (response.isSuccessful) {
                    success(response.body())
                } else {
                    success(null)
                }
            }

            override fun onFailure(call: Call<List<Reservacion>>, t: Throwable) {
                failure(t)
            }
        })
    }
}