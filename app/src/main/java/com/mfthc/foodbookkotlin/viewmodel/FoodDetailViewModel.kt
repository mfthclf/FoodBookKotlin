package com.mfthc.foodbookkotlin.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mfthc.foodbookkotlin.model.Food
import com.mfthc.foodbookkotlin.roomdb.FoodDB
import kotlinx.coroutines.launch


class FoodDetailViewModel(application: Application) : AndroidViewModel(application) {

    val foodLiveData = MutableLiveData<Food>()

    fun getRoomData(id:Int){
        viewModelScope.launch {
            val dao = FoodDB(getApplication()).getDao()
            val food = dao.getFood(id)
            foodLiveData.value=food
        }
    }
}