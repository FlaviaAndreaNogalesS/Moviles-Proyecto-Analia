package com.example.loginapi.ui.activities

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.loginapi.R
import com.example.loginapi.databinding.ActivityReservationsBinding
import com.example.loginapi.models.Reservacion
import com.example.loginapi.repositories.RetrofitRepository
import com.example.loginapi.ui.viewmodels.ReservationsViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReservationsActivity : AppCompatActivity() {
  private lateinit var binding: ActivityReservationsBinding
  private val model: ReservationsViewModel by viewModels()
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
   binding = ActivityReservationsBinding.inflate(layoutInflater)
    setContentView(binding.root)

    ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
      val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
      v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
      insets
    }
    model.loadReservations(this)

    model.reservations.observe(this) { reservations ->
      val adapter = ArrayAdapter(this, android.R.layout.activity_list_item, reservations.map { it.toString() })
      binding.listViewReservas.adapter = adapter
    }

    model.errorMessage.observe(this) {
      if (it.isNotEmpty()) {
        Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
      }
    }
  }

}