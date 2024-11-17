package com.example.currencyratesapp

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.currencyratesapp.item.ExchangeRate

@Composable
fun CurrencyApp(viewModel: CurrencyViewModel) {
    var date by remember { mutableStateOf(TextFieldValue("")) }
    val rates by viewModel.getRates(date.text).observeAsState(emptyList())
    var loading by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Input Field for Date
        TextField(
            value = date,
            onValueChange = {
                date = it
            },
            label = { Text("Enter date (DD.MM.YYYY)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Fetch Rates Button
        Button(
            onClick = {
                loading = true
                viewModel.getRates(date.text)
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Fetch Rates")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Loading Indicator
        if (loading && rates.isEmpty()) {
            Text("Loading...", style = MaterialTheme.typography.bodyMedium)
        }

        // Display Rates or Error Message
        if (rates.isEmpty() && !loading) {
            Text(
                "No rates available or error occurred.",
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.bodyMedium
            )
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(rates) { rate ->
                    CurrencyItem(rate)
                }
            }
        }

        // Stop Loading when data is fetched
        if (rates.isNotEmpty()) {
            loading = false
        }
    }
}
