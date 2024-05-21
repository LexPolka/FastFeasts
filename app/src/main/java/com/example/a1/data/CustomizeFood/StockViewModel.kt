package com.example.a1.data.CustomizeFood

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a1.data.cartData.CartRepository
import com.example.a1.data.profiledata.Profile
import com.example.a1.data.profiledata.ProfileEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class StockViewModel(private val stockRepository: StockRepository) : ViewModel(){

    private val _quantityFlow = MutableStateFlow<Int?>(null)
    val quantityFlow: StateFlow<Int?> = _quantityFlow
    val allIngredients: StateFlow<List<StockEntity>> = stockRepository.allIngredients.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    private val _uiState = MutableStateFlow(Stock())
    val uiState: StateFlow<Stock> = _uiState.asStateFlow()

    fun getQuantityById(id: Int){
        viewModelScope.launch {
            val stockEntity = stockRepository.getIngredientById(id)
            _quantityFlow.value = stockEntity?.quantity

        }
    }

    fun deleteAllIngredients() {
        viewModelScope.launch {
            stockRepository.deleteAllIngredients()
            stockRepository.resetAutoIncrement()
        }
    }
    fun updateQuantity(id: Int, quantity: Int) {
        viewModelScope.launch {
            val currentIngredient = stockRepository.getIngredientById(id)
            if (currentIngredient != null) {
                val newQuantity = currentIngredient.quantity - quantity
                stockRepository.updateIngredientQuantity(id, newQuantity)
                _uiState.update { currentState ->
                    currentState.copy(quantity = newQuantity)
                }
            }
        }
    }


    fun SetQuantity(id: Int, quantity : Int) {
        viewModelScope.launch {
            val currentIngredient = stockRepository.getIngredientById(id)
            if (currentIngredient != null) {
                stockRepository.updateIngredientQuantity(id, quantity)
                _uiState.update { currentState ->
                    currentState.copy(quantity = quantity)
                }
            }
        }
    }

    fun SaveIngredient(name: String, quantity: Int){
        val ingredient = StockEntity(
            name = name,
            quantity = quantity,
        )

        viewModelScope.launch {
            try {
                stockRepository.insertIngredient(ingredient)
            } catch (e: Exception) {
                //Womp womp
            }
        }
    }

    fun deleteAllStock() {
        viewModelScope.launch{
            stockRepository.deleteAllStock()
        }

    }
}


data class Stock(
    val name:String = "",
    val quantity:Int = 0
)