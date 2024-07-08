package com.example.loginapi.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginapi.models.Restaurant
import com.example.loginapi.models.dto.SearchRequestDTO
import com.example.loginapi.repositories.UserRepository
import kotlinx.coroutines.launch

class RestauranteViewModel: ViewModel() {
  private val _restaurants = MutableLiveData<List<Restaurant>>()
  val restaurants: LiveData<List<Restaurant>> get() = _restaurants

  init {
    fetchRestaurants(SearchRequestDTO())
  }

  fun fetchRestaurants(searchRequest: SearchRequestDTO) {
    viewModelScope.launch {
      UserRepository.buscarRestaurantes(
        SearchRequestDTO(),
        { restaurants ->
          _restaurants.value = restaurants ?: emptyList()
        },
        { error ->
          // Manejar error
          _restaurants.value = emptyList()
        }
      )
    }
  }
}
