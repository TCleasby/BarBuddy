package com.example.barbuddy

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class DrinkViewModel: ViewModel() {
    var drinkItemLiveData: LiveData<List<DrinkItem>> = MutableLiveData()
    private val drinkFetcher =  DrinkFetcher()

    init {
        drinkItemLiveData = drinkFetcher.fetchDrink()
    }

    fun getNewDrink() {
        drinkItemLiveData = DrinkFetcher().fetchDrink()
    }
}