package com.example.a1.data.CustomizeFood

import androidx.lifecycle.LiveData


class StockOfflineRepository(private val stockDao: StockDao) : StockRepository {
    override val allIngredients: List<StockEntity> = stockDao.getAllIngredients()

    override suspend fun insertIngredient(stockEntity: StockEntity) {
        stockDao.insertIngredient(stockEntity)
    }

    override suspend fun updateIngredientQuantity(id: Int, quantity: Int) {
        stockDao.updateIngredientQuantity(id, quantity)
    }
}
