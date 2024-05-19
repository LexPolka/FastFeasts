package com.example.test

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a1.R




class FoodMenuViewModel : ViewModel() {

    //===GET THE ID OF EACH CATEGORY===============
    var idBun: Int = 15
    var idPatty: Int = 15
    var idLettuce: Int = 15
    var idSauce: Int = 15
    var idExtra: Int = 15

    fun clearIDafterCart(){
        idBun = 15
        idPatty= 15
        idLettuce = 15
        idSauce = 15
        idExtra = 15
    }


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
    inner class Burger(val name: String, val Price: Double, val Image: Int,val Buns: Buns, val Patty: Patty, val Lettuce: Lettuce, val Sauce: Sauce, val Extra: Extra)

    var emptySlotBurger = Burger("Customize Burger",TotalPrice, R.drawable.burgericon, addBun, addPatty, addLettuce, addSauce, addExtra)

    private val addBurger = mutableStateListOf<Burger>()
    val cartBurger: List<Burger> get() = addBurger

    fun clearBurger(){
        addBun = emptySlot1
        addPatty = emptySlot2
        addLettuce = emptySlot3
        addSauce = emptySlot4
        addExtra = emptySlot5

    }
    fun AddToCartBurger(newBurger: Burger){
        addBurger.add(newBurger)
    }

    fun CustomBurgertotalPrice(){
        TotalPrice = addBun.price + addPatty.price + addLettuce.price + addSauce.price + addExtra.price

    }


}



