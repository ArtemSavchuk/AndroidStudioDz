package com.example.currencyratesapp

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import android.util.Log

interface PrivatBankApi {
    @GET("p24api/exchange_rates?json")
    fun getExchangeRates(
        @Query("date") date: String
    ): Call<ExchangeRatesResponse>
}

data class ExchangeRatesResponse(
    val date: String,
    val exchangeRate: List<CurrencyRate>
)