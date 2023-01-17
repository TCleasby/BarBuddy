package com.example.barbuddy.DrinkApi

import com.example.barbuddy.DrinkResponse
import retrofit2.Call
import retrofit2.http.GET

interface DrinkApi {
    @GET("api/json/v1/1/random.php")
    fun fetchDrink(): Call<DrinkResponse>
}