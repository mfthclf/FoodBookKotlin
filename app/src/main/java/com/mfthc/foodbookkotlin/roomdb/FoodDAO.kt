package com.mfthc.foodbookkotlin.roomdb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.mfthc.foodbookkotlin.model.Food

@Dao
interface FoodDAO {

    @Insert
    suspend fun insertAll(vararg food: Food): List<Long>

    @Query("SELECT * FROM Food")
    suspend fun getAllFood(): List<Food>

    @Query("SELECT * FROM Food WHERE id = :id")
    suspend fun getFood(id: Int): Food

    @Query("DELETE FROM Food")
    suspend fun deleteAllFood()
}