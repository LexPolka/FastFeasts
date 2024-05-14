package com.example.a1.data.staffdata

import androidx.lifecycle.ViewModel
import com.example.a1.data.cartData.Food
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class Order(
    val id: String,
    val items: MutableList<Food> = mutableListOf()
)

class StaffViewModel : ViewModel() {
    private val _orderList = MutableStateFlow<List<Order>>(emptyList())
    val orderList: StateFlow<List<Order>> = _orderList.asStateFlow()

    // Adds a new order to the order list
    fun addOrder(order: Order) {
        _orderList.update { currentOrders ->
            currentOrders + order
        }
    }

    // Removes an order from the order list
    fun removeOrder(order: Order) {
        _orderList.update { currentOrders ->
            currentOrders - order
        }
    }

    // Gets the total price of a specific order
    fun getOrderTotalPrice(orderId: String): Double {
        val order = _orderList.value.find { it.id == orderId }
        return order?.items?.sumOf { it.price.toDoubleOrNull() ?: 0.0 } ?: 0.0
    }

    // Clears all orders
    fun clearAllOrders() {
        _orderList.value = emptyList()
    }

    // Clear all items from a specific order
    fun clearOrder(orderId: String) {
        _orderList.update { currentOrders ->
            currentOrders.map { order ->
                if (order.id == orderId) {
                    order.copy(items = mutableListOf())
                } else {
                    order
                }
            }
        }
    }
}
