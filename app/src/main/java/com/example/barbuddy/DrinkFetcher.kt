package com.example.barbuddy

import com.example.barbuddy.DrinkApi.DrinkApi
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class DrinkFetcher {

    private val drinkApi: DrinkApi

    init {

    //  Additional logging capabilities ////////////////////////////////////
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://thecocktaildb.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()

        drinkApi = retrofit.create(DrinkApi::class.java)
    }

    fun fetchDrink(): LiveData<List<DrinkItem>> {
        val responseLiveData: MutableLiveData<List<DrinkItem>> = MutableLiveData()
        val drinkRequest: Call<DrinkResponse> = drinkApi.fetchDrink()

        drinkRequest.enqueue(object: Callback<DrinkResponse>{
            override fun onFailure(call: Call<DrinkResponse>, t: Throwable) {
                Log.e("DrinkFetcher: ", "Failed to fetch drink", t)
            }

            override fun onResponse(call: Call<DrinkResponse>, response: Response<DrinkResponse>) {
                val drinkResponse: DrinkResponse? = response.body()
                var drinkItems: List<DrinkItem> = drinkResponse?.drinks ?: mutableListOf()
                Log.d("Drink Items", drinkItems.size.toString())
                responseLiveData.value = drinkItems
            }
        })

     return responseLiveData
    }
}