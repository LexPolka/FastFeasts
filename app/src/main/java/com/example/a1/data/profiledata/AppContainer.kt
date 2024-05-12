package com.example.a1.data.profiledata

import android.content.Context

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