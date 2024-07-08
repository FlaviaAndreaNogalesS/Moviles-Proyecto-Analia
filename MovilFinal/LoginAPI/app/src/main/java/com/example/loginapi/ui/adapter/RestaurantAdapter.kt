package com.example.loginapi.ui.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.motion.widget.MotionScene.Transition.TransitionOnClick
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.loginapi.databinding.ItemRestaurantBinding
import com.example.loginapi.models.Restaurant
import com.example.loginapi.ui.activities.RestaurantDetailActivity

class RestaurantAdapter(private var restaurants: List<Restaurant>) :
  RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
          RestaurantViewHolder {
    val binding = ItemRestaurantBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    return RestaurantViewHolder(binding)
  }

  override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
    holder.bind(restaurants[position])
  }

  override fun getItemCount() = restaurants.size
  fun setRestaurants(restaurants: List<Restaurant>) {
    this.restaurants = restaurants
    notifyDataSetChanged()
  }
  class RestaurantViewHolder(private val binding: ItemRestaurantBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(restaurant: Restaurant) {
      binding.tvRestaurantName.text = restaurant.name
      binding.tvRestaurantLocation.text = restaurant.city
      binding.root.setOnClickListener {
          // Handle on click
        Toast.makeText(binding.root.context, "Clicked on ${restaurant.name}", Toast.LENGTH_SHORT).show()

        val context = binding.root.context
        val intent = Intent(context, RestaurantDetailActivity::class.java)
        intent.putExtra("RESTAURANT_ID", restaurant.id)
        context.startActivity(intent)
      }

    }
  }
}