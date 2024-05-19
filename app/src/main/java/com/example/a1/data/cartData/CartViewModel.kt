package com.example.a1.data.cartData

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


data class Food(
    val image: ByteArray = byteArrayOf(),
    val name : String = "",
    val price : String = "", //use string cuz for int, just do .toInt()
)


class CartViewModel(private val cartRepository: CartRepository) : ViewModel() {


    //StateFlows
    //for FoodEntity in the cart
    private val _cartItems = MutableStateFlow<List<FoodEntity>>(emptyList())
    val cartItems: StateFlow<List<FoodEntity>> = _cartItems.asStateFlow()

    //food
    private val _foods = MutableStateFlow<List<Food>>(emptyList())
    val foods: StateFlow<List<Food>> = _foods.asStateFlow()

    private val items = mutableStateListOf<Food>()
    val cart: List<Food> get() = items

    private val _isCartInitialized = mutableStateOf(false)
    val isCartInitialized: State<Boolean> get() = _isCartInitialized

    init {
        // Collecting FoodEntity items from the repository and updating the cart list
        viewModelScope.launch {
            cartRepository.getCart().collect { orders ->
                _cartItems.value = orders.filterNotNull()
                _foods.value = getExistingCartItemsFromDatabase(orders.filterNotNull())

                // Check if cart is empty and initialize if necessary
                if (cart.isEmpty()) {
                    initializeCart() }

                _isCartInitialized.value = true
            }
        }
    }


    fun addToDatabase(food: Food) {
        val foodEntity = FoodEntity(
            name = food.name,
            image = food.image,
            price = food.price
        )
        viewModelScope.launch {
            cartRepository.addItem(foodEntity)
        }
    }

    fun removeFromDatabase(food: Food) {
        viewModelScope.launch {
            val foodEntity = FoodEntity(
                name = food.name,
                image = food.image,
                price = food.price
            )
            viewModelScope.launch {
                cartRepository.removeItem(foodEntity)
            }
        }

    }

    fun getTotalPrice(): Double {
    return cartItems.value.sumOf { it.price.toDoubleOrNull() ?: 0.0 }
    }

    fun getTotalItems(): Int {
        return cartItems.value.size
    }

    fun clearCart() {
        viewModelScope.launch {
            cartRepository.clearCart()
        }
    }


    // Function to initialize the cart with items from the database
    fun initializeCart() {
        _cartItems.value = convertFoodListToFoodEntityList(foods.value)
    }

    //get from db
    fun getExistingCartItemsFromDatabase(foodEntities: List<FoodEntity>): List<Food> {
        return foodEntities.map { foodEntity ->
            Food(
                image = foodEntity.image,
                name = foodEntity.name,
                price = foodEntity.price
            )
        }
    }

    //convert to db
    fun convertFoodListToFoodEntityList(foodList: List<Food>): List<FoodEntity> {
        val foodEntityList = mutableListOf<FoodEntity>()
        for (food in foodList) {
            val foodEntity = FoodEntity(
                id = 0, // Assuming you set the ID appropriately in the database
                name = food.name,
                image = food.image,
                price = food.price
            )
            foodEntityList.add(foodEntity)
        }
        return foodEntityList
    }

    //both
    fun addToCart(foodItem: Food) {
        items.add(foodItem)
        addToDatabase(foodItem)
    }

    fun removeFromCart(food: Food) {
        items.remove(food)
        removeFromDatabase(food)
    }


    fun clearFoodCart() {
        items.clear()
        clearCart()
    }


    // dialogs
    var isRemoveDialogShown by mutableStateOf(false)
        private set

    fun onRemoveFromCartClick() {
        isRemoveDialogShown = true
    }

    fun onRemoveFromCartDismissClick() {
        isRemoveDialogShown = false
    }

    var isClearCartDialogShown by mutableStateOf(false)
        private set

    fun onClearCartClick() {
        isClearCartDialogShown = true
    }

    fun onClearCartDismissClick() {
        isClearCartDialogShown = false
    }



}