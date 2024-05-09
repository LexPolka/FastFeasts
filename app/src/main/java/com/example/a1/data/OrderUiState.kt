/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.a1.data

import android.net.Uri
import androidx.compose.foundation.isSystemInDarkTheme

data class OrderUiState(
    //restaurant location to order from
    val restaurantLocation : String = "",
    //profile picture
    val profilePictureUri: Uri?,
    val name: String = "",
    val day : String = "",
    val month : String = "",
    val year : String = "",
    val phoneNumber : String = "",
)

data class Food(
    val profilePictureUri: Uri?,
    val name : String = "",
    val price : String = "", //use string cuz for int, just do .toInt()
)
//for khoo, idk how to implement yet
class Cart {
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
        return items.sumByDouble { it.price.toDoubleOrNull() ?: 0.0 }
    }

    fun clearCart() {
        items.clear()
    }
}