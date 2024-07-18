package com.mfthc.foodbookkotlin.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mfthc.foodbookkotlin.model.Food

@Database(entities = [Food::class], version = 1)
abstract class FoodDB : RoomDatabase() {
    abstract fun getDao(): FoodDAO

    companion object {

        @Volatile
        private var instance: FoodDB? = null

        private val lock = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(lock) {
            instance ?: createDataBase(context).also {
                instance = it
            }
        }

        private fun createDataBase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            FoodDB::class.java,
            "FoodDataBase"
        ).build()
    }
}