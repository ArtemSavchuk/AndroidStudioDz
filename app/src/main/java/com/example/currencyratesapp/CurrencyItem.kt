package com.example.currencyratesapp

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.currencyratesapp.item.ExchangeRate

@Composable
fun CurrencyItem(rate: ExchangeRate) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(text = "Currency: ${rate.currency}")
                Text(text = "Base: ${rate.baseCurrency}")
            }
            Column {
                Text(text = "Buy: ${rate.purchaseRate ?: rate.purchaseRateNB}")
                Text(text = "Sale: ${rate.saleRate ?: rate.saleRateNB}")
            }
        }
    }
}
