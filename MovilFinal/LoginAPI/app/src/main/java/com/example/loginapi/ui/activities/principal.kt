package com.example.loginapi.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.loginapi.R
import com.example.loginapi.databinding.ActivityPrincipalBinding

class principal : AppCompatActivity() {
  lateinit var  binding: ActivityPrincipalBinding
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    binding = ActivityPrincipalBinding.inflate(layoutInflater)
    setContentView(binding.root)

    ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
      val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
      v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
      insets
    }
     stupEventLsitener()
  }

  private fun stupEventLsitener() {
 binding.iniciar.setOnClickListener{
   intent = Intent(this, MainActivity::class.java)
   startActivity(intent)
 }
    binding.register.setOnClickListener{
      intent = Intent(this, Registro::class.java)
      startActivity(intent)
    }

    binding.visitante.setOnClickListener{
      intent = Intent(this, RestaurantListActivity::class.java)
      startActivity(intent)
    }
  }
}