package com.mfthc.foodbookkotlin.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mfthc.foodbookkotlin.model.Food

@Database(entities = [Food::class], version = 1)
abstract class FoodDB : RoomDatabase() {
    abstract fun getDao(): FoodDAO
}