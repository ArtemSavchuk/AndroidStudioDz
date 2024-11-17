package com.example.currencyratesapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import com.example.currencyratesapp.item.ExchangeRatesResponse

interface PrivatBankApi {
    @GET("p24api/exchange_rates?json")
    fun getExchangeRates(
        @Query("date") date: String
    ): Call<ExchangeRatesResponse>
}
