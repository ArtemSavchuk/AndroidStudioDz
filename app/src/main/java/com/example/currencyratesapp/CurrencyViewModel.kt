package com.example.currencyratesapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class CurrencyViewModel : ViewModel() {
    private val repository = CurrencyRepository()

    fun getRates(date: String): LiveData<List<CurrencyRate>> {
        Log.d("CurrencyViewModel", "getRates: ${repository.getRates(date)}")
        return repository.getRates(date)
    }
}