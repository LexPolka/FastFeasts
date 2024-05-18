package com.example.a1.data.staffdata

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.a1.data.cartData.Food
import com.example.a1.data.profiledata.ProfileRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class Stock(
    val name : String = "",
    var quantity : Int = 0,
    val image: Int = 0
)

class StaffViewModel(private val repository: StaffRepository) : ViewModel() {
    private val _orderList = MutableStateFlow<List<OrderEntity>>(emptyList())
    val orderList: StateFlow<List<OrderEntity>> = _orderList.asStateFlow()

    //Is a list of IndividualFood
    private val _individualFoodList = MutableStateFlow<List<IndividualFood>>(emptyList())
    val individualFoodList: StateFlow<List<IndividualFood>> = _individualFoodList.asStateFlow()

    //observing changes in orders/food
    init {
        viewModelScope.launch {
            repository.getAllIndividualFood().collect { foodItems ->
                _individualFoodList.value = foodItems.filterNotNull()
            }
        }
        viewModelScope.launch {
            repository.getAllOrders().collect { orders ->
                _orderList.value = orders.filterNotNull()
            }
        }
    }

    fun deleteOrder(orderID : String){
        viewModelScope.launch {
            repository.deleteOrder(orderID)
        }
    }

    fun addToOrder(orderID : String, name : String, price : String, image : ByteArray){
        val newOrder = OrderEntity(
            orderID = orderID,
            name = name,
            price = price,
            image = image,
        )

        viewModelScope.launch {
            repository.insertOrder(newOrder)
        }
    }

    fun clearAllOrders(){
        viewModelScope.launch {
            repository.clearAllOrders()
        }
    }

    // Convert image URI to byte array
    // val imageInputStream = context.contentResolver.openInputStream(imageUri)
    // val imageData = imageInputStream?.readBytes()

    //Convert ByteArray to Bitmap
    //val imageFromByteToBitmap = imageBitmapFromBytes(food.image)
    fun addIndividualFood(foodName : String, foodPrice : String, imageData : ByteArray){
        val food = IndividualFood(
            name = foodName,
            price = foodPrice,
            image = imageData
        )
        viewModelScope.launch {
            repository.insertIndividualFood(food)
        }
    }

    fun removeIndividualFood(food: IndividualFood){
        viewModelScope.launch {
            repository.deleteIndividualFood(food)
        }
    }

    fun getAllIndividualFood(){
        viewModelScope.launch {
            repository.getAllIndividualFood().collect { foodItems ->
                _individualFoodList.value = foodItems.filterNotNull()
            }
        }
    }

    fun deleteAllIndividualFood(){
        viewModelScope.launch {
            repository.deleteAllIndividualFood()
        }
    }
}
