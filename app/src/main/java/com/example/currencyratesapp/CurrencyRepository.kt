package com.example.currencyratesapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import android.util.Log

class CurrencyRepository {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.privatbank.ua/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val api = retrofit.create(PrivatBankApi::class.java)

    fun getRates(date: String): LiveData<List<CurrencyRate>> {
        val ratesLiveData = MutableLiveData<List<CurrencyRate>>()

        api.getExchangeRates(date).enqueue(object : retrofit2.Callback<ExchangeRatesResponse> {
            override fun onResponse(
                call: retrofit2.Call<ExchangeRatesResponse>,
                response: retrofit2.Response<ExchangeRatesResponse>
            ) {
                if (response.isSuccessful) {
                    ratesLiveData.value = response.body()?.exchangeRate
                    Log.d("PrivatBankApi", "Response: ${response}")
                }
                else {
                    Log.e("PrivatBankApi", "Error: ${response.message()}")
                }
            }

            override fun onFailure(call: retrofit2.Call<ExchangeRatesResponse>, t: Throwable) {
                ratesLiveData.value = emptyList()
            }
        })

        return ratesLiveData
    }
}