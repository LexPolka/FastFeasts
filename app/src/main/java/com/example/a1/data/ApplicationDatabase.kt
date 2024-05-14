package com.example.a1.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.a1.data.profiledata.ProfileDao
import com.example.a1.data.profiledata.ProfileEntity

@Database(entities = [ProfileEntity::class], version = 3, exportSchema = false)
abstract class ApplicationDatabase : RoomDatabase() {
    abstract fun profileDao(): ProfileDao

    companion object {
        @Volatile
        private var Instance: ApplicationDatabase? = null
        fun getDatabase(context: Context): ApplicationDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, ApplicationDatabase::class.java, "profile_database")
                    .build().also { Instance = it }
            }
        }
    }
}