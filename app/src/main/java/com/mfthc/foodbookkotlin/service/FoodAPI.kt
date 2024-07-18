package com.mfthc.foodbookkotlin.service

import com.mfthc.foodbookkotlin.model.Food
import retrofit2.http.GET

interface FoodAPI {

    //BASE-URL -> https://raw.githubusercontent.com/
    //ENDPOINT -> atilsamancioglu/BTK20-JSONVeriSeti/master/besinler.json

    @GET("atilsamancioglu/BTK20-JSONVeriSeti/master/besinler.json")
    suspend fun getFood() : List<Food>
}