package com.example.a1.ui

import android.net.Uri
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModel
import com.example.a1.data.Profile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class FFViewModel : ViewModel() {
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

    fun setPhone(number: String)
    {
        _uiState.update {currentState ->
            currentState.copy(phoneNumber = number)
        }
    }
}