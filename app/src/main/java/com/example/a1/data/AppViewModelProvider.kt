package com.example.a1.data

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.a1.data.CustomizeFood.StockViewModel
import com.example.a1.data.cartData.CartViewModel
import com.example.a1.data.profiledata.ProfileViewModel
import com.example.a1.data.staffdata.StaffViewModel
import com.example.test.FoodMenuViewModel

/**
 * Provides Factory to create instance of ViewModel for the entire Inventory app
 */
//below is code that should call the factory
//viewModel: ProfileViewModel = viewModel(factory = AppViewModelProvider.Factory)
object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            ProfileViewModel(FastFeastsApplication().container.profileRepository)
        }

        initializer {
            StaffViewModel(FastFeastsApplication().container.staffRepository)
        }

        initializer {
            CartViewModel(FastFeastsApplication().container.cartRepository)
        }

        initializer {
            StockViewModel(FastFeastsApplication().container.stockRepository)
        }

    }
}

fun CreationExtras.FastFeastsApplication(): Application =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as Application)