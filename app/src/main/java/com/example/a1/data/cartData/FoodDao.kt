package com.example.a1.data.cartData

import android.content.ClipData.Item
import androidx.lifecycle.LiveData
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
    suspend fun addFood(food: FoodEntity)

    @Delete
    suspend fun removeFood(food: FoodEntity)

    @Query("SELECT * FROM food")
    fun getCart(): Flow<List<Food>>

    @Query("DELETE FROM food")
    suspend fun clearCart()



}
