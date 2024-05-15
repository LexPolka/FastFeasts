package com.example.a1.data.staffdata

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface StaffDao {
    //Orders from User
    @Query("SELECT * FROM orders")
    fun getAllOrders(): Flow<List<OrderEntity?>>
    @Query("SELECT * FROM orders WHERE id = :orderId")
    fun getOrderById(orderId: String): Flow<OrderEntity?>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrder(order: OrderEntity)
    @Delete
    suspend fun deleteOrder(order: OrderEntity)
    @Query("DELETE FROM orders")
    suspend fun clearAllOrders()

    //Individual Items
    @Query("SELECT * FROM individualFoodItems")
    fun getAllIndividualFood() : Flow<List<IndividualFood?>>
    @Query("SELECT * FROM individualFoodItems WHERE id = :foodID")
    fun getIndividualFoodById(foodID: String): Flow<IndividualFood?>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIndividualFood(order: IndividualFood)
    @Delete
    suspend fun deleteIndividualFood(order: IndividualFood)
    @Query("DELETE FROM individualFoodItems")
    suspend fun clearAllIndividualFood()
}