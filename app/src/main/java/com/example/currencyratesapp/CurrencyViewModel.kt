package com.example.currencyratesapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.currencyratesapp.item.ExchangeRate

class CurrencyViewModel : ViewModel() {
    private val repository = CurrencyRepository()

    fun getRates(date: String): LiveData<List<ExchangeRate>> {
        return repository.getRates(date)
    }
}
