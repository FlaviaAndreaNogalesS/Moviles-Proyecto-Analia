package com.example.loginapi.ui.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.loginapi.models.Reservacion
import com.example.loginapi.repositories.PreferencesRepository
import com.example.loginapi.repositories.UserRepository

class ReservationsViewModel: ViewModel() {
  private val _reservations: MutableLiveData<List<Reservacion>> = MutableLiveData()
  val reservations: LiveData<List<Reservacion>> get() = _reservations

  private val _errorMessage: MutableLiveData<String> = MutableLiveData("")
  val errorMessage: LiveData<String> get() = _errorMessage

  fun loadReservations(context: Context) {
    val token = PreferencesRepository.getToken(context) ?: return
    UserRepository.obtenerReservas(token, success = {
      _reservations.value = it ?: emptyList()
    }, failure = {
      _errorMessage.value = it.message
    })
  }
}