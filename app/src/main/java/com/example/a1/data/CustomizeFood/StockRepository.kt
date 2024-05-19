package com.example.a1.data.CustomizeFood

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow


interface StockRepository {
    val allIngredients: Flow<List<StockEntity>>

    suspend fun insertIngredient(stockEntity: StockEntity)

    suspend fun updateIngredientQuantity(id: Int, quantity: Int)

    suspend fun getIngredientById(id: Int): StockEntity?
}