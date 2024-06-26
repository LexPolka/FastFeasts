package com.example.a1.data.staffdata

import kotlinx.coroutines.flow.Flow

interface StaffRepository {
    fun getAllOrders(): Flow<List<OrderEntity?>>
    fun getOrderById(orderId: String): Flow<OrderEntity?>
    fun insertOrder(order: OrderEntity)
    suspend fun deleteOrder(orderID: String)
    suspend fun clearAllOrders()

    fun getAllIndividualFood(): Flow<List<IndividualFood?>>
    fun getIndividualFoodById(foodID: String): Flow<IndividualFood?>
    suspend fun insertIndividualFood(food: IndividualFood)
    suspend fun deleteIndividualFood(food: IndividualFood)
    suspend fun deleteAllIndividualFood()
}