package com.example.a1.data.profiledata

import androidx.compose.runtime.currentComposer
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class IndividualFoodItem(
    val image: ByteArray = byteArrayOf(),
    val name : String = "",
    val price : String = "", //use string cuz for int, just do .toInt()
)

data class RestaurantLocation(
    val location : String = ""
)

class GlobalViewModel : ViewModel() {
    private val _foodState = MutableStateFlow(IndividualFoodItem())
    val foodState: StateFlow<IndividualFoodItem> = _foodState.asStateFlow()

    private val _restaurant = MutableStateFlow(RestaurantLocation())
    val restaurant: StateFlow<RestaurantLocation> = _restaurant.asStateFlow()


    //Setters
    fun setDisplayIndividualFood(image: ByteArray, name : String, price : String)
    {
        _foodState.update {currentState ->
            currentState.copy(name = name, price = price, image = image)
        }
    }

    fun setLocation(location : String){
        _restaurant.update { currentState ->
            currentState.copy(location = location)
        }
    }
}

