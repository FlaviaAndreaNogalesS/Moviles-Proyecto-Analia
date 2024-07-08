package com.example.loginapi.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginapi.models.Restaurant
import com.example.loginapi.repositories.UserRepository
import kotlinx.coroutines.launch

class RestaurantDetailViewModel : ViewModel() {
  private val _restaurant = MutableLiveData<Restaurant?>()
  val restaurant: LiveData<Restaurant?> get() = _restaurant

  fun fetchRestaurantDetails(restaurantId: Int) {
    viewModelScope.launch {
      UserRepository.obtenerRestaurantePorId(
        restaurantId,
        { restaurant ->
          _restaurant.value =restaurant
        },
        { error ->
          // Manejar error
          _restaurant.value = null

        }
      )
    }
  }
}