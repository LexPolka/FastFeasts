package com.example.a1.data.staffdata

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.a1.data.cartData.Food
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Entity(tableName = "orders")
data class OrderEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val orderID : String = "",
    val image: ByteArray,
    val name : String = "",
    val price : String = "",
)

@Entity(tableName = "individualFoodItems")
data class IndividualFood(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val image: ByteArray,
    val name : String = "",
    val price : String = "",
)