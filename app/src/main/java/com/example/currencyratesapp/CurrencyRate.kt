package com.example.currencyratesapp

data class CurrencyRate(
    val ccy: String,
    val base_ccy: String,
    val buy: String,
    val sale: String
)