package com.example.a1.data.profiledata

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.a1.data.staffdata.Order

class ProfileViewModel(private val repository: ProfileRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(Profile())
    val uiState: StateFlow<Profile> = _uiState.asStateFlow()

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

    fun setCreditCard(ccNumber : String, ccCode : String, ccMonth : String, ccYear: String){
        _uiState.update { currentState ->
            currentState.copy(ccNumber = ccNumber, ccCode= ccCode, ccMonth=ccMonth, ccYear=ccYear)
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
            phoneNumber = _uiState.value.phoneNumber,
            ccNumber = _uiState.value.ccNumber,
            ccCode = _uiState.value.ccCode,
            ccMonth = _uiState.value.ccMonth,
            ccYear = _uiState.value.ccYear
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

    fun deleteAllProfiles(){
        viewModelScope.launch {
            repository.deleteAllProfile()
        }
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
    val isStaff : Boolean = false,

    val ccNumber : String = "",
    val ccMonth : String = "",
    val ccYear : String = "",
    val ccCode : String = "",
)