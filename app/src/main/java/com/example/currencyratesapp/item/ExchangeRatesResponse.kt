package com.example.currencyratesapp.item

import com.google.gson.annotations.SerializedName

data class ExchangeRatesResponse(
    @SerializedName("date")
    val date: String?,
    @SerializedName("bank")
    val bank: String?,
    @SerializedName("baseCurrency")
    val baseCurrency: Int?,
    @SerializedName("baseCurrencyLit")
    val baseCurrencyLit: String?,
    @SerializedName("exchangeRate")
    val exchangeRate: List<ExchangeRate>?
)
