package com.example.a1.data.CustomizeFood

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.a1.data.profiledata.ProfileEntity

@Dao
interface StockDao {
    @Query("SELECT * FROM StockEntity")
    fun getAllIngredients(): List<StockEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertIngredient(stockEntity: StockEntity)

    @Query("UPDATE StockEntity SET quantity = :quantity WHERE id = :id")
    suspend fun updateIngredientQuantity(id: Int, quantity: Int)

}