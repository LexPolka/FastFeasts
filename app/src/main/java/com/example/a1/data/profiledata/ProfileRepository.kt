    package com.example.a1.data.profiledata

    import kotlinx.coroutines.flow.Flow

    interface ProfileRepository {

        fun getProfile(email: String): Flow<ProfileEntity?>

        suspend fun insertProfile(profile: ProfileEntity)

        suspend fun deleteProfile(profile: ProfileEntity)
    }