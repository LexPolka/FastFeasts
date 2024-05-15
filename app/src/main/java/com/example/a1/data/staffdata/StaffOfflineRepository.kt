package com.example.a1.data.staffdata

import kotlinx.coroutines.flow.Flow

class StaffOfflineRepository(private val staffDao: StaffDao) : StaffRepository {

    override fun getAllOrders(): Flow<List<OrderEntity?>> = staffDao.getAllOrders()

    override fun getOrderById(orderId: String): Flow<OrderEntity?> = staffDao.getOrderById(orderId)

    override suspend fun insertOrder(order: OrderEntity) = staffDao.insertOrder(order)

    override suspend fun deleteOrder(order: OrderEntity) = staffDao.deleteOrder(order)

    override suspend fun clearAllOrders() = staffDao.clearAllOrders()
}