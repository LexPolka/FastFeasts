package com.example.a1.data.profiledata

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.a1.data.staffdata.OrderEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProfileDao {
    //use getProfile to get profile when LOGIN
    @Query("SELECT * FROM profiles WHERE email = :email LIMIT 1")
    fun getProfile(email: String): Flow<ProfileEntity?>

    //use insertProfile when registering
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProfile(profile: ProfileEntity)

    //if user wants to delete account
    //should also clear cart and other databases
    @Query("DELETE FROM profiles WHERE email = :email")
    suspend fun deleteProfiles(email : String)

    @Query ("DELETE FROM profiles")
    suspend fun deleteAllProfiles()
}