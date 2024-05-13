package com.example.a1.data.profiledata

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a1.data.cartData.Food
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProfileViewModel(private val repository: ProfileRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(Profile())
    val uiState: StateFlow<Profile> = _uiState.asStateFlow()

    var profileList: List<ProfileEntity?> = mutableListOf()

    //Setters
    fun setProfilePictureUri(uri: Uri?) {
        _uiState.update { currentState ->
            currentState.copy(profilePictureUri = uri.toString())
        }
    }

    fun setName(name: String) {
        _uiState.update { currentState ->
            currentState.copy(name = name)
        }
    }

    fun setDate(day: String, month: String, year: String) {
        _uiState.update { currentState ->
            currentState.copy(day = day, month = month, year = year)
        }
    }

    fun setPhone(number: String) {
        _uiState.update { currentState ->
            currentState.copy(phoneNumber = number)
        }
    }

    // Function to save profile data
    fun saveProfile() {
        val profile = ProfileEntity(
            profilePictureUri = _uiState.value.profilePictureUri,
            name = _uiState.value.name,
            email = _uiState.value.email,
            password = _uiState.value.password,
            day = _uiState.value.day,
            month = _uiState.value.month,
            year = _uiState.value.year,
            phoneNumber = _uiState.value.phoneNumber
        )

        viewModelScope.launch {
            try {
                repository.insertProfile(profile)
                // Update UI or show success message if needed
            } catch (e: Exception) {
                // Handle error (e.g., log, show error message)
            }
        }
    }
    fun getAllProfiles() : List<ProfileEntity?> {
        return repository.getAllProfiles()
    }
}

data class Profile(
    //profile picture
    val profilePictureUri: String = "",
    val name: String = "",
    val email: String ="",
    val password: String = "",
    val day : String = "",
    val month : String = "",
    val year : String = "",
    val phoneNumber : String = "",
)