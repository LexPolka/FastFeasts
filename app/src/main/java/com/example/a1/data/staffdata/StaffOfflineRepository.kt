package com.example.a1.data.staffdata

import kotlinx.coroutines.flow.Flow

class StaffOfflineRepository(private val staffDao: StaffDao) : StaffRepository {

    override fun getAllOrders(): Flow<List<OrderEntity?>> = staffDao.getAllOrders()
    override fun getOrderById(orderId: String): Flow<OrderEntity?> = staffDao.getOrderById(orderId)
    override fun insertOrder(order: OrderEntity) = staffDao.insertOrder(order)
    override suspend fun deleteOrder(order: OrderEntity) = staffDao.deleteOrder(order)
    override suspend fun clearAllOrders() = staffDao.clearAllOrders()

    override fun getAllIndividualFood(): Flow<List<IndividualFood?>> = staffDao.getAllIndividualFood()
    override fun getIndividualFoodById(foodID: String): Flow<IndividualFood?> = staffDao.getIndividualFoodById(foodID)
    override suspend fun insertIndividualFood(food: IndividualFood) = staffDao.insertIndividualFood(food)
    override suspend fun deleteIndividualFood(food: IndividualFood) = staffDao.deleteIndividualFood(food)
    override suspend fun deleteAllIndividualFood() = staffDao.deleteAllIndividualFood()
}