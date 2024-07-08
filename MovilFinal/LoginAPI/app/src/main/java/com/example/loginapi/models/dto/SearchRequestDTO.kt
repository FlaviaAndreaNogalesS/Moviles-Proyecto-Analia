package com.example.loginapi.models.dto

data class SearchRequestDTO(
  val fecha: String? = null,
  val horaInicio: String? = null,
  val horaFin: String? = null,
  val ciudad: String? = null
)