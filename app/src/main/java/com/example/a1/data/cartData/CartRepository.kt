package com.example.a1.data.cartData

import kotlinx.coroutines.flow.Flow

interface CartRepository {

    fun getCart(id: Int): Flow<Food>

    suspend fun insert(food: Food)

    suspend fun delete(food: Food)



}