package com.example.a1.data.profiledata

import kotlinx.coroutines.flow.Flow

interface ProfileRepository {

    fun getProfile(email: String): List<ProfileEntity?>

    // Function to get all profiles from the database
    suspend fun getAllProfiles(): List<ProfileEntity?>

    suspend fun insertProfile(profile: ProfileEntity)

    suspend fun deleteProfile(profile: ProfileEntity)
}