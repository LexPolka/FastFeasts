package com.example.a1.data.CustomizeFood

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a1.data.cartData.CartRepository
import com.example.a1.data.profiledata.Profile
import com.example.a1.data.profiledata.ProfileEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class StockViewModel(private val stockRepository: StockRepository) : ViewModel(){
    private val _uiState = MutableStateFlow(Stock())
    val uiState: StateFlow<Stock> = _uiState.asStateFlow()



    fun SetIngredientName(Name : String) {
        _uiState.update { currentState ->
            currentState.copy(name = Name)
        }
    }

    fun SetQuantity(Quantity : Int) {
        _uiState.update { currentState ->
            currentState.copy(quantity = Quantity)
        }
    }

    fun SaveIngredient(){
        val ingredient = StockEntity(
            name = _uiState.value.name,
            quantity = _uiState.value.quantity,
        )

        viewModelScope.launch {
            try {
                stockRepository.insertIngredient(ingredient)
            } catch (e: Exception) {

            }
        }
    }
}


data class Stock(
    val name:String = "",
    val quantity:Int = 0
)