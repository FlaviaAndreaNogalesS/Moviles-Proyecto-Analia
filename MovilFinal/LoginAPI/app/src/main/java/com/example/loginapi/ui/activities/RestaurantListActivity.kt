package com.example.loginapi.ui.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.loginapi.R
import com.example.loginapi.databinding.ActivityRestaurantListBinding
import com.example.loginapi.models.Restaurant
import com.example.loginapi.models.dto.SearchRequestDTO
import com.example.loginapi.ui.adapters.RestaurantAdapter
import com.example.loginapi.ui.viewmodels.RestauranteViewModel

class RestaurantListActivity : AppCompatActivity() {
  private lateinit var binding: ActivityRestaurantListBinding
  private val viewModel: RestauranteViewModel by viewModels()
  private val adapter = RestaurantAdapter(emptyList())

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    binding = ActivityRestaurantListBinding.inflate(layoutInflater)
    setContentView(binding.root)

    ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
      val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
      v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
      insets
    }
    setupRecyclerView()
    observeViewModel()
   // viewModel.fetchRestaurants()
    binding.btnFilter.setOnClickListener {
      val searchRequest = SearchRequestDTO(
        fecha = binding.etSelectedDate.text.toString(),
        horaInicio = binding.etStartTime.text.toString(),
        horaFin = binding.etEndTime.text.toString(),
        ciudad = binding.etCity.text.toString()
      )
      viewModel.fetchRestaurants(searchRequest)
    }
  }

  private fun observeViewModel() {
    viewModel.restaurants.observe(this) { restaurants ->
      adapter.setRestaurants(restaurants)
//    viewModel.restaurants.observe(this) { restaurants ->
//      val adapter = RestaurantAdapter(restaurants)
//      binding.restaurantList.adapter = adapter
//    }
    }
    }

    private fun setupRecyclerView() {
      binding.restaurantList.layoutManager = LinearLayoutManager(this)
      binding.restaurantList.adapter = adapter
      //RestaurantAdapter(emptyList())
    }

  }



