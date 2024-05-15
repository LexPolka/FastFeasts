package com.example.a1.data.cartData

import kotlinx.coroutines.flow.Flow

class CartOfflineRepository( private val foodDao: FoodDao): CartRepository{

    override fun getCart(id: Int): Flow<FoodEntity> = foodDao.getCart(id)

    override suspend fun insert(food: FoodEntity) = foodDao.insert(food)

    override suspend fun delete(food: FoodEntity) = foodDao.delete(food)
}
