package com.example.loginapi.ui.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.loginapi.R
import com.example.loginapi.databinding.ActivityRestaurantDetailBinding
import com.example.loginapi.ui.viewmodels.RestaurantDetailViewModel

class RestaurantDetailActivity : AppCompatActivity() {
  private lateinit var binding: ActivityRestaurantDetailBinding
  private val viewModel: RestaurantDetailViewModel by viewModels()
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityRestaurantDetailBinding.inflate(layoutInflater)
    setContentView(binding.root)
    ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
      val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
      v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
      insets
    }
    val restaurantId = intent.getIntExtra("RESTAURANT_ID", -1)
    if (restaurantId != -1) {
      viewModel.fetchRestaurantDetails(restaurantId)
    }

    observeViewModel()
  }

  private fun observeViewModel() {
    viewModel.restaurant.observe(this) { restaurant ->
      if (restaurant != null) {
        binding.tvRestaurantName.text = restaurant.name
        binding.tvRestaurantDescription.text = restaurant.description
        // Configura el resto de vistas con los datos del restaurante
      } else {
        // Manejar el caso donde el restaurante es null, mostrar un mensaje de error o similar
        //binding.tvRestaurantName.text = getString(R.string.restaurant_not_found)
        binding.tvRestaurantDescription.text = ""
      }
    }
  }
}