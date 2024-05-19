package com.example.a1.data.cartData

import androidx.lifecycle.LiveData
import com.example.a1.data.staffdata.OrderEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext


class CartOfflineRepository( private val foodDao: FoodDao): CartRepository {
    override suspend fun addItem(food: FoodEntity) = foodDao.addFood(food)

    override suspend fun removeItem(food: FoodEntity) = foodDao.removeFood(food)

    override fun getCart(): Flow<List<FoodEntity>> = foodDao.getCart()

    override suspend fun clearCart() = foodDao.clearCart()



}
