package com.example.a1.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.a1.data.CustomizeFood.StockDao
import com.example.a1.data.CustomizeFood.StockEntity
import com.example.a1.data.cartData.FoodDao
import com.example.a1.data.cartData.FoodEntity
import com.example.a1.data.profiledata.ProfileDao
import com.example.a1.data.profiledata.ProfileEntity
import com.example.a1.data.staffdata.IndividualFood
import com.example.a1.data.staffdata.OrderEntity
import com.example.a1.data.staffdata.StaffDao

@Database(entities = [ProfileEntity::class, OrderEntity::class, IndividualFood::class, FoodEntity::class, StockEntity::class], version = 4, exportSchema = false)
abstract class ApplicationDatabase : RoomDatabase() {
    abstract fun profileDao(): ProfileDao
    abstract fun staffDao(): StaffDao
    abstract fun foodDao(): FoodDao
    abstract fun StockDao(): StockDao


    companion object {
        @Volatile
        private var Instance: ApplicationDatabase? = null
        fun getDatabase(context: Context): ApplicationDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, ApplicationDatabase::class.java, "database")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build().also { Instance = it }
            }
        }
    }
}