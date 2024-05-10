package com.example.a1.data.profiledata

import ProfileRepository
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProfileViewModel(private val repository: ProfileRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(Profile(profilePictureUri = null))
    val uiState: StateFlow<Profile> = _uiState.asStateFlow()

    //Setters
    fun setProfilePictureUri(uri: Uri?) {
        _uiState.update{currentState ->
            currentState.copy(profilePictureUri = uri)
        }
    }
    fun setName(name : String){
        _uiState.update {currentState ->
            currentState.copy(name = name)
        }
    }
    fun setDate(day: String, month: String, year: String)
    {
        _uiState.update {currentState ->
            currentState.copy(day = day, month = month, year = year)
        }
    }
    fun setPhone(number: String) {
        _uiState.update { currentState ->
            currentState.copy(phoneNumber = number)
        }
    }

    //Repository
    fun getProfile(name: String, password: String): Flow<ProfileEntity?> {
        return repository.getProfile(name, password)
    }

    fun insertProfile(profile: ProfileEntity) {
        viewModelScope.launch {
            repository.insertProfile(profile)
        }
    }

    fun deleteProfile(profile: ProfileEntity) {
        viewModelScope.launch {
            repository.deleteProfile(profile)
        }
    }
}

data class Profile(
    //restaurant location to order from
    val restaurantLocation : String = "",
    //profile picture
    val profilePictureUri: Uri?,
    val name: String = "",
    val password: String = "",
    val day : String = "",
    val month : String = "",
    val year : String = "",
    val phoneNumber : String = "",
)