package com.example.a1.data.staffdata

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.a1.data.cartData.Food
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Entity(tableName = "orders")
@TypeConverters(FoodListConverter::class)
data class OrderEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String = "",
    val items: MutableList<Food> = mutableListOf()
)

@Entity(tableName = "individualFoodItems")
@TypeConverters(FoodListConverter::class)
data class IndividualFood(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val image: ByteArray?,
    val name : String = "",
    val price : String = "",
)

class FoodListConverter {
    @TypeConverter
    fun fromFoodList(value: MutableList<Food>?): String {
        val gson = Gson()
        val type = object : TypeToken<MutableList<Food>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toFoodList(value: String): MutableList<Food>? {
        val gson = Gson()
        val type = object : TypeToken<MutableList<Food>>() {}.type
        return gson.fromJson(value, type)
    }
}