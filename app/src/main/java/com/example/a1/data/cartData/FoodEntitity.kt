package com.example.a1.data.cartData

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity(tableName = "food")
data class FoodEntity(
    @PrimaryKey (autoGenerate = true) val id : Int = 0,
    val image: ByteArray = byteArrayOf(),
    val name : String = "",
    val price : String = "", //use string cuz for int, just do .toInt()
)