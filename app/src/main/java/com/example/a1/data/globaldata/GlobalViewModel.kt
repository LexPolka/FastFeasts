package com.example.a1.data.profiledata

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GlobalViewModel : ViewModel() {
    private val _foodState = MutableStateFlow(Food())
    val foodState: StateFlow<Food> = _foodState.asStateFlow()

    //Setters
    fun setDisplayIndividualFood(image: Int, name : String, price : String)
    {
        _foodState.update {currentState ->
            currentState.copy(name = name, price = price, image = image)
        }
    }
}

