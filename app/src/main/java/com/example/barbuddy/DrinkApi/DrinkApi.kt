package com.example.barbuddy.DrinkApi

import com.example.barbuddy.DrinkResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface DrinkApi {
    @GET("api/json/v1/1/random.php")
    fun fetchDrink(): Call<DrinkResponse>
}