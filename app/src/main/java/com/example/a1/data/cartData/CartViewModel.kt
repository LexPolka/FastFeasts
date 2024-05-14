package com.example.a1.data.cartData

import androidx.compose.foundation.lazy.layout.LazyLayoutPinnedItemList
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import coil.compose.rememberImagePainter


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

    fun removeFromCart(food: Food) {
        items.remove(food)
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