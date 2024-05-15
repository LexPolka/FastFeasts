package com.example.a1.data.staffdata

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a1.data.cartData.Food
import com.example.a1.data.profiledata.ProfileRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class Order(
    val id: String,
    val items: MutableList<Food> = mutableListOf()
)

class StaffViewModel(private val repository: StaffRepository) : ViewModel() {
    private val _orderList = MutableStateFlow<List<Order>>(emptyList())
    val orderList: StateFlow<List<Order>> = _orderList.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getAllOrders().collect { orders ->
                _orderList.value = orders.map { it?.let { Order(it.id, it.items) } ?: Order("", mutableListOf()) }
            }
        }
    }

    fun addOrder(order: Order) {
        val orderToEntity = OrderEntity(
            id = order.id,
            items = order.items
        )
        viewModelScope.launch {
            repository.insertOrder(orderToEntity)
        }
    }

    fun removeOrder(order: Order) {
        val orderToEntity = OrderEntity(
            id = order.id,
            items = order.items
        )
        viewModelScope.launch {
            repository.deleteOrder(orderToEntity)
        }
    }

    fun getOrderTotalPrice(orderId: String): Double {
        val order = _orderList.value.find { it.id == orderId }
        return order?.items?.sumOf { it.price.toDoubleOrNull() ?: 0.0 } ?: 0.0
    }

    fun clearAllOrders() {
        viewModelScope.launch {
            repository.clearAllOrders()
        }
    }

    fun clearOrder(orderId: String) {
        val orderToEntity = OrderEntity(
            id = orderId
        )

        viewModelScope.launch {
            val order = _orderList.value.find { it.id == orderId }
            if (order != null) {
                repository.insertOrder(OrderEntity(orderToEntity.id, mutableListOf()))
            }
        }
    }
}
