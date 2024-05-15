package com.example.a1.data.cartData

import kotlinx.coroutines.flow.Flow

class CartOfflineRepository( private val foodDao: FoodDao): FoodDao{

    override fun getCart(id: Int): Flow<Food> = foodDao.getCart(id)

    override suspend fun insert(food: Food) = foodDao.insert(food)

    override suspend fun delete(food: Food) = foodDao.delete(food)



}
