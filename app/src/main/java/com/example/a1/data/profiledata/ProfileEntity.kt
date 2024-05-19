package com.example.a1.data.profiledata

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "profiles")
data class ProfileEntity(
    //profile picture
    val profilePictureUri: ByteArray = byteArrayOf(),
    val name: String = "",
    val password: String = "",
    val day : String = "",
    val month : String = "",
    val year : String = "",
    val phoneNumber : String = "",
    val isStaff : Boolean = false,

    val ccNumber : String = "",
    val ccMonth : String = "",
    val ccYear : String = "",
    val ccCode : String = "",

    @PrimaryKey(autoGenerate = false)
    val email: String = "",
)