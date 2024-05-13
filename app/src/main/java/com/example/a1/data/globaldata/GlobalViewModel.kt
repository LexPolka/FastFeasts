package com.example.a1.data.profiledata

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class IndividualFoodItem(
    val image: Int = 0,
    val name : String = "",
    val price : String = "", //use string cuz for int, just do .toInt()
)

class GlobalViewModel : ViewModel() {
    private val _foodState = MutableStateFlow(IndividualFoodItem())
    val foodState: StateFlow<IndividualFoodItem> = _foodState.asStateFlow()

    //Setters
    fun setDisplayIndividualFood(image: Int, name : String, price : String)
    {
        _foodState.update {currentState ->
            currentState.copy(name = name, price = price, image = image)
        }
    }
}

