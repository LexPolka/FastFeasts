package com.example.a1.data.CustomizeFood

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "StockEntity")
data class StockEntity(
    val name: String,
    var quantity: Int,

    @PrimaryKey(autoGenerate = true)
    val id : Int = 0, ){

}


