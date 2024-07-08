package com.example.loginapi.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.loginapi.R
import com.example.loginapi.databinding.ActivityUserAccessBinding

class UserAccessActivity : AppCompatActivity() {
  private lateinit var binding: ActivityUserAccessBinding
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityUserAccessBinding.inflate(layoutInflater)
    setContentView(binding.root)

    ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
      val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
      v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
      insets
    }

    binding.btnMisReservas.setOnClickListener {
      startActivity(Intent(this, ReservationsActivity::class.java))
    }

    binding.btnCrearReserva.setOnClickListener {
      startActivity(Intent(this, CreateReservationActivity::class.java))
    }

    binding.btnCrearRestaurante.setOnClickListener {
      startActivity(Intent(this, CreateRestaurantActivity::class.java))
    }
  }
}