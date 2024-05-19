package com.example.a1.data.profiledata

import kotlinx.coroutines.flow.Flow

class ProfileOfflineRepository(private val profileDao: ProfileDao) : ProfileRepository {

    override fun getProfile(email: String): List<ProfileEntity?> = profileDao.getProfile(email)

    override suspend fun insertProfile(profile: ProfileEntity) = profileDao.insertProfile(profile)

    override suspend fun deleteProfile(profile: ProfileEntity) = profileDao.deleteProfiles(profile)

    override suspend fun login(email: String, password: String): Boolean {
        val profile = profileDao.getProfile(email).firstOrNull()
        return profile != null && profile.password == password
    }
}