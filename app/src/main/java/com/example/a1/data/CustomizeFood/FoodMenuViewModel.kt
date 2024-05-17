package com.example.test

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.a1.R
import kotlinx.coroutines.flow.MutableStateFlow

data class Stock(
    val name : String = "",
    var quantity : Int = 0,
    val image: Int = 0
)

class FoodMenuViewModel : ViewModel() {
    //==DIALOG FOR BURGER BUNS SELECTION=====================================================
    var openDialogBun by mutableStateOf(false)
        private set

    fun onBunClick(){
        openDialogBun = true
    }

    fun onBunDismissClick(){
        openDialogBun = false
    }


    //==DIALOG FOR PATTY SELECTION===========================================================
    var openDialogPatty by mutableStateOf(false)
        private set

    fun onPattyClick(){
        openDialogPatty = true
    }

    fun onPattyDismissClick(){
        openDialogPatty = false
    }

    //==DIALOG FOR LETTUCE SELECTION=========================================================
    var openDialogLettuce by mutableStateOf(false)
        private set

    fun onLettuceClick(){
        openDialogLettuce = true
    }

    fun onLettuceDismissClick(){
        openDialogLettuce = false
    }

    //==DIALOG FOR SAUCE SELECTION==========================================================
    var openDialogSauce by mutableStateOf(false)
        private set

    fun onSauceClick(){
        openDialogSauce = true
    }

    fun onSauceDismissClick(){
        openDialogSauce = false
    }

    //==DIALOG FOR EXTRA SELECTION==========================================================
    var openDialogExtra by mutableStateOf(false)
        private set

    fun onExtraClick(){
        openDialogExtra = true
    }

    fun onExtraDismissClick(){
        openDialogExtra = false
    }


// ============LIST OF INGREDIENTS==============================================

    //-------------BUNS-------------
    inner class Buns(val name: String, val price: Double, val image: Int)
    var emptySlot1 = Buns("[EMPTY SLOT]",0.00, R.drawable.emptyslot)
    var addBun by mutableStateOf<Buns>(emptySlot1)
        private set


    fun addedNewBun(newBun:Buns){
        addBun = newBun

    }

    //-------------PATTY-------------
    inner class Patty(val name: String, val price: Double, val image: Int)
    var emptySlot2 = Patty("[EMPTY SLOT]",0.00, R.drawable.emptyslot)
    var addPatty by mutableStateOf<Patty>(emptySlot2)
        private set

    fun addedNewPatty(newPatty:Patty){
        addPatty = newPatty

    }

    //-------------LETTUCE-------------
    inner class Lettuce(val name: String, val price: Double, val image: Int)
    var emptySlot3 = Lettuce("[EMPTY SLOT]",0.00, R.drawable.emptyslot)
    var addLettuce by mutableStateOf<Lettuce>(emptySlot3)
        private set


    fun addedNewLettuce(newLettuce:Lettuce){
        addLettuce = newLettuce

    }

    //-------------SAUCE-------------
    inner class Sauce(val name: String, val price: Double, val image: Int)
    var emptySlot4 = Sauce("[EMPTY SLOT]",0.00, R.drawable.emptyslot)
    var addSauce by mutableStateOf<Sauce>(emptySlot4)
        private set


    fun addedNewSauce(newSauce:Sauce){
        addSauce = newSauce

    }

    //-------------EXTRA-------------
    inner class Extra(val name: String, val price: Double, val image: Int)
    var emptySlot5 = Extra("[EMPTY SLOT]",0.00, R.drawable.emptyslot)
    var addExtra by mutableStateOf<Extra>(emptySlot5)
        private set


    fun addedNewExtra(newExtra:Extra){
        addExtra = newExtra

    }

    //==============
    var TotalPrice by mutableStateOf(0.00)
    inner class Burger(val Buns: Buns, val Patty: Patty, val Lettuce: Lettuce, val Sauce: Sauce, val Extra: Extra)
    inner class BurgerDisplayInCart(val Name: String, val Price: Double, val Image: Int)
    var emptySlotBurger = BurgerDisplayInCart("",TotalPrice, R.drawable.burgericon)
    var addBurger by mutableStateOf<BurgerDisplayInCart>(emptySlotBurger)
    private val _burgerState = MutableStateFlow(BurgerDisplayInCart("", TotalPrice, R.drawable.burgericon))

    fun CustomBurgertotalPrice(){
        TotalPrice = addBun.price + addPatty.price + addLettuce.price + addSauce.price + addExtra.price

    }
    fun addedNewBurgerInCart(newBurger: BurgerDisplayInCart){
        addBurger = newBurger

    }
}

