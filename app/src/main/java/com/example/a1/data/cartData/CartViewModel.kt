package com.example.a1.data.cartData

import androidx.compose.foundation.lazy.layout.LazyLayoutPinnedItemList
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import coil.compose.rememberImagePainter
import kotlinx.coroutines.flow.StateFlow


data class Food(
    val image: Int = 0,
    val name : String = "",
    val price : String = "", //use string cuz for int, just do .toInt()

)

class CartViewModel : ViewModel() {
    private val items: MutableList<Food> = mutableListOf()
    val cartItems: MutableList<Food> = items


    fun addToCart(foodItem: Food) {
        items.add(foodItem)
    }

    fun removeFromCart(food: Food) {
        items.remove(food)
    }

    fun getTotalPrice(): Double {
        return items.sumOf { it.price.toDoubleOrNull() ?: 0.0 }
    }

    fun clearCart() {
        items.clear()
    }

    var isRemoveDialogShown by mutableStateOf(false)
        private set

    fun onRemoveFromCartClick(){
        isRemoveDialogShown = true
    }

    fun onRemoveFromCartDismissClick(){
        isRemoveDialogShown = false
    }

    var isClearCartDialogShown by mutableStateOf(false)
        private set

    fun onClearCartClick(){
        isRemoveDialogShown = true
    }

    fun onClearCartDismissClick(){
        isRemoveDialogShown = false
    }


}