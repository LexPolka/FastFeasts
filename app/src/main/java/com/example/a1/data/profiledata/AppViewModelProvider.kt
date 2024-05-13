package com.example.a1.data.profiledata

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.a1.data.profiledata.ProfileApplication
import com.example.a1.data.profiledata.ProfileViewModel

/**
 * Provides Factory to create instance of ViewModel for the entire Inventory app
 */
//below is code that should call the factory
//viewModel: ProfileViewModel = viewModel(factory = AppViewModelProvider.Factory)
object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            ProfileViewModel(profileApplication().container.profileRepository)
        }
    }
}

fun CreationExtras.profileApplication(): ProfileApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as ProfileApplication)