package com.example.a1.data.cartData

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel


data class Food(
    val image: ByteArray = byteArrayOf(),
    val name : String = "",
    val price : String = "", //use string cuz for int, just do .toInt()
)


class CartViewModel(cartRepository: CartRepository) : ViewModel() {
    private val items = mutableStateListOf<Food>()
    val cartItems: List<Food> get() = items

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

    fun onRemoveFromCartClick() {
        isRemoveDialogShown = true
    }

    fun onRemoveFromCartDismissClick() {
        isRemoveDialogShown = false
    }

    var isClearCartDialogShown by mutableStateOf(false)
        private set

    fun onClearCartClick(){
        isClearCartDialogShown = true
    }

    fun onClearCartDismissClick(){
        isClearCartDialogShown = false
    }


}