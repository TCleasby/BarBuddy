package com.example.barbuddy

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class DrinkViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DrinkViewModel() as T
    }
}