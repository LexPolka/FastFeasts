package com.example.a1.data.CustomizeFood

import androidx.lifecycle.LiveData


interface StockRepository {
    val allIngredients: List<StockEntity>

    suspend fun insertIngredient(stockEntity: StockEntity)

    suspend fun updateIngredientQuantity(id: Int, quantity: Int)
}