package com.example.loginapi.ui.activities

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.loginapi.R
import com.example.loginapi.databinding.ActivityRegistroBinding
import com.example.loginapi.models.RegisterRequestDTO
import com.example.loginapi.ui.viewmodels.RegisterViewModel

class Registro : AppCompatActivity() {
  private lateinit var binding: ActivityRegistroBinding
  private val registerViewModel: RegisterViewModel by viewModels()
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityRegistroBinding.inflate(layoutInflater)
    setContentView(binding.root)

    try {
      ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
        val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
        v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
        insets

      }
    } catch (e: Exception) {
      Log.e("RegistroActivity", "Error al aplicar los insets", e)
    }

    binding.btnRegister.setOnClickListener {
      val name = binding.etName.text.toString()
      val email = binding.etEmail.text.toString()
      val password = binding.etPassword.text.toString()
      val phone = binding.etPhone.text.toString()

      if (name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && phone.isNotEmpty()) {
        val registerRequestDTO = RegisterRequestDTO(name, email, password, phone)
        registerViewModel.registerUser(name, email, password, phone)
      } else {
        Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show()
      }
    }

    registerViewModel.registerResult.observe(this, { response ->
      if (response != null) {
        Log.d("RegistroActivity", "User registered successfully: $response")
        Toast.makeText(this, "Usuario registrado exitosamente", Toast.LENGTH_SHORT).show()
        // Navegar a la siguiente actividad o cerrar esta actividad
      } else {
        Log.e("RegistroActivity", "Error en el registro: Response is null")
        Toast.makeText(this, "Error en el registro", Toast.LENGTH_SHORT).show()
      }
    })

    registerViewModel.error.observe(this, { throwable ->
      Log.e("RegistroActivity", "Error en el registro", throwable)
      Toast.makeText(this, "Error: ${throwable.message}", Toast.LENGTH_SHORT).show()
    })

  }
}