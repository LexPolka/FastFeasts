package com.example.a1.data.cartData

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "food")
data class FoodEntity(
    @PrimaryKey (autoGenerate = true)
    val id : Int = 0,
    val image: Int = 0,
    val name : String = "",
    val price : String = "", //use string cuz for int, just do .toInt()
)