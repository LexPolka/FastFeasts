package com.example.a1.data.cartData

import android.content.ClipData.Item
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface FoodDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(food: FoodEntity)

    @Delete
    suspend fun delete(food: FoodEntity)

    @Query("SELECT * FROM food WHERE id = :id")
    fun getCart(id: Int): Flow<FoodEntity>



}
