package com.example.a1.data.profiledata

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "profiles")
data class ProfileEntity(
    //profile picture
    val profilePictureUri: String = "",
    val name: String = "",
    val password: String = "",
    val day : String = "",
    val month : String = "",
    val year : String = "",
    val phoneNumber : String = "",

    @PrimaryKey(autoGenerate = false)
    val email: String = "",
)