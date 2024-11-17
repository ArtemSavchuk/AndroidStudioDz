package com.example.currencyratesapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.observe
import androidx.compose.ui.Alignment

@Composable
fun CurrencyApp(viewModel: CurrencyViewModel) {
    var date by remember { mutableStateOf(TextFieldValue("")) }
    var rates by remember { mutableStateOf(listOf<CurrencyRate>()) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        TextField(
            value = date,
            onValueChange = { date = it },
            label = { Text("Enter date (DD.MM.YYYY)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                viewModel.getRates(date.text).observeForever { newRates ->
                    rates = newRates
                }
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Fetch Rates")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(rates) { rate ->
                CurrencyItem(rate)
            }
        }
    }
}