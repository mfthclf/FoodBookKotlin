package com.mfthc.foodbookkotlin.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mfthc.foodbookkotlin.model.Food
import com.mfthc.foodbookkotlin.roomdb.FoodDB
import com.mfthc.foodbookkotlin.service.FoodAPIService
import com.mfthc.foodbookkotlin.util.SpecialSharedPreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FoodListViewModel(application: Application) : AndroidViewModel(application) {

    val foods = MutableLiveData<List<Food>>()
    val foodErrorMessage = MutableLiveData<Boolean>()
    val foodLoading = MutableLiveData<Boolean>()

    private val foodApiService = FoodAPIService()
    private val specialSharedPreferences = SpecialSharedPreferences(getApplication())

    private fun GetDataFromNet() {

        foodLoading.value = true

        viewModelScope.launch(Dispatchers.IO) {
            val foodList = foodApiService.getData()
            withContext(Dispatchers.Main) {
                foodLoading.value = false
                SaveToRoom(foodList)
            }
        }
    }
    private fun showFoods(foodList: List<Food>){
        foods.value = foodList
        foodErrorMessage.value=false
        foodLoading.value=false
    }

    private fun SaveToRoom(foodList: List<Food>) {
        viewModelScope.launch {
            val dao = FoodDB(getApplication()).getDao()
            dao.deleteAllFood()
            val idList = dao.insertAll(*foodList.toTypedArray()) // -> List > Varargs
            var i = 0
            while (i < foodList.size) {
                foodList[i].id = idList[i].toInt()
                i += 1
            }
            showFoods(foodList)
        }
        specialSharedPreferences.saveTime(System.nanoTime())

    }
}