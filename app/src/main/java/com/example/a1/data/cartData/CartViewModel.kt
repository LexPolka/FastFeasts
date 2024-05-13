package com.example.a1.data.cartData

import androidx.lifecycle.ViewModel


data class Food(
    val image: Int = 0,
    val name : String = "",
    val price : String = "", //use string cuz for int, just do .toInt()

)

class CartViewModel : ViewModel() {
    private val items: MutableList<Food> = mutableListOf()

    fun addToCart(foodItem: Food) {
        items.add(foodItem)
    }

    fun removeFromCart(foodItem: Food) {
        items.remove(foodItem)
    }

    fun getCartItems(): List<Food> {
        return items.toList()
    }

    fun getTotalPrice(): Double {
        return items.sumOf { it.price.toDoubleOrNull() ?: 0.0 }
    }


    fun clearCart() {
        items.clear()
    }



}