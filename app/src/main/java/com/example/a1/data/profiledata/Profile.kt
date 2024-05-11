package com.example.a1.data.profiledata

import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "profiles")
data class ProfileEntity(
    //restaurant location to order from
    val restaurantLocation : String = "",
    //profile picture
    val profilePictureUri: String = "",
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val day : String = "",
    val month : String = "",
    val year : String = "",
    val phoneNumber : String = "",

    @PrimaryKey(autoGenerate = true)
    val id : Int = 0
)