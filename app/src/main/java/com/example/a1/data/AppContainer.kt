package com.example.a1.data

import android.content.Context
import com.example.a1.data.CustomizeFood.StockOfflineRepository
import com.example.a1.data.CustomizeFood.StockRepository

import com.example.a1.data.cartData.CartOfflineRepository
import com.example.a1.data.cartData.CartRepository
import com.example.a1.data.profiledata.ProfileOfflineRepository
// com.example.a1.data.profiledata.ProfileOfflineRepository
import com.example.a1.data.profiledata.ProfileRepository
import com.example.a1.data.staffdata.StaffOfflineRepository
import com.example.a1.data.staffdata.StaffRepository

/**
 * App container for Dependency injection. <- dependency injection (google it)
 */
interface AppContainer {
    val profileRepository: ProfileRepository

    val staffRepository : StaffRepository

    val cartRepository: CartRepository

    val stockRepository: StockRepository

}

class AppDataContainer(private val context: Context) : AppContainer {
    /**
     * Implementation for [ProfileRepository]
     */
    override val profileRepository: ProfileRepository by lazy {
        ProfileOfflineRepository(ApplicationDatabase.getDatabase(context).profileDao())
    }

    override val staffRepository: StaffRepository by lazy {
        StaffOfflineRepository(ApplicationDatabase.getDatabase(context).staffDao())
    }

    override val cartRepository: CartRepository by lazy {
        CartOfflineRepository(ApplicationDatabase.getDatabase(context).foodDao())
    }

    override val stockRepository: StockRepository by lazy {
        StockOfflineRepository(ApplicationDatabase.getDatabase(context).stockDao())
    }




}
