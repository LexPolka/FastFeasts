package com.example.a1.data.staffdata

import kotlinx.coroutines.flow.Flow

interface StaffRepository {
    fun getAllOrders(): Flow<List<OrderEntity?>>

    fun getOrderById(orderId: String): Flow<OrderEntity?>

    suspend fun insertOrder(order: OrderEntity)

    suspend fun deleteOrder(order: OrderEntity)

    suspend fun clearAllOrders()
}