package com.example.a1.data.cartData

import com.example.a1.data.staffdata.OrderEntity
import java.util.concurrent.Flow


interface CartRepository {

    suspend fun addItem(food: FoodEntity)
    suspend fun removeItem(food: FoodEntity)
    fun getCart(): kotlinx.coroutines.flow.Flow<List<FoodEntity>>

    suspend fun clearCart()


}