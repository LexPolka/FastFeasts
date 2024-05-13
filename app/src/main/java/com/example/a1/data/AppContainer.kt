package com.example.a1.data

import android.content.Context
import com.example.a1.data.profiledata.ProfileDatabase
import com.example.a1.data.profiledata.ProfileOfflineRepository
import com.example.a1.data.profiledata.ProfileRepository

/**
 * App container for Dependency injection. <- dependency injection (google it)
 */
interface AppContainer {
    val profileRepository: ProfileRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    /**
     * Implementation for [ProfileRepository]
     */
    override val profileRepository: ProfileRepository by lazy {
        ProfileOfflineRepository(ProfileDatabase.getDatabase(context).profileDao())
    }
}