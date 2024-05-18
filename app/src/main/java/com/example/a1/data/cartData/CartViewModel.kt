package com.example.a1.data.cartData

import android.graphics.BitmapFactory
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.nio.ByteBuffer


data class Food(
    val image: ByteArray = byteArrayOf(),
    val name : String = "",
    val price : String = "", //use string cuz for int, just do .toInt()
)


class CartViewModel(private val cartRepository: CartRepository) : ViewModel() {

    //Food
    private val items = mutableStateListOf<Food>()
    val cart: List<Food> get() = items

    fun addToCart(foodItem: Food) {
        items.add(foodItem)
    }

    fun removeFromCart(food: Food) {
        items.remove(food)
    }

    fun getTotalCartPrice(): Double {
        return items.sumOf { it.price.toDoubleOrNull() ?: 0.0 }
    }

    fun getTotalCartItems(): Int {
        var counter = 0
        for (x in cart){
            counter++
        }

        return counter

    }

    fun clearFoodCart() {
        items.clear()
    }


    //FoodEntity
    private val _cartItems = MutableStateFlow<List<FoodEntity>>(emptyList())
    val cartItems: StateFlow<List<FoodEntity>> = _cartItems.asStateFlow()

    fun addToCart(food: FoodEntity) {
        viewModelScope.launch{
            cartRepository.addItem(food)
        }
    }

    fun removeFromCart(food: FoodEntity) {
        viewModelScope.launch{
            cartRepository.removeItem(food)
        }
    }

    fun getTotalPrice(): Double {
        return cartItems.value.sumOf { it.price.toDoubleOrNull() ?: 0.0 }
    }

    fun getTotalItems(): Int {
        return cartItems.value.size
    }

    fun clearCart() {
        viewModelScope.launch{
            cartRepository.clearCart()
        }
    }

    //Convert food -> foodEntity


    //dialogs
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

    fun intToByteArray(value: Int): ByteArray {
        return ByteBuffer.allocate(4).putInt(value).array()
    }

    fun imageBitmapFromBytes(bytes: ByteArray): ImageBitmap {
        val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
        return bitmap.asImageBitmap()
    }

    fun byteArrayToInt(byteArray: ByteArray, littleEndian: Boolean = false): Int {
        val buffer = ByteBuffer.wrap(byteArray)
        if (littleEndian) {
            buffer.order(java.nio.ByteOrder.LITTLE_ENDIAN)
        } else {
            buffer.order(java.nio.ByteOrder.BIG_ENDIAN)
        }
        return buffer.int
    }


}