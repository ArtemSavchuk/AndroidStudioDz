package com.example.currencyratesapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.currencyratesapp.item.ExchangeRatesResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.currencyratesapp.PrivatBankApi
import com.example.currencyratesapp.item.ExchangeRate

class CurrencyRepository {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.privatbank.ua/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val api = retrofit.create(PrivatBankApi::class.java)


    fun getRates(date: String): LiveData<List<ExchangeRate>> {
        val ratesLiveData = MutableLiveData<List<ExchangeRate>>()

        api.getExchangeRates(date).enqueue(object : Callback<ExchangeRatesResponse> {
            override fun onResponse(
                call: Call<ExchangeRatesResponse>,
                response: Response<ExchangeRatesResponse>
            ) {
                if (response.isSuccessful) {
                    ratesLiveData.value = response.body()?.exchangeRate ?: emptyList()
                } else {
                    ratesLiveData.value = emptyList()
                }
            }

            override fun onFailure(call: Call<ExchangeRatesResponse>, t: Throwable) {
                ratesLiveData.value = emptyList()
            }
        })

        return ratesLiveData
    }
}