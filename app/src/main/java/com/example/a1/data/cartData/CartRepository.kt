package com.example.a1.data.cartData

import kotlinx.coroutines.flow.Flow

interface CartRepository {

    fun getCart(id: Int): Flow<FoodEntity>

    suspend fun insert(food: FoodEntity)

    suspend fun delete(food: FoodEntity)



}