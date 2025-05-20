package com.example.currencyconverter

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var fromSpinner: Spinner
    private lateinit var toSpinner: Spinner
    private lateinit var amountInput: EditText
    private lateinit var resultText: TextView
    private lateinit var convertButton: Button
    private lateinit var swapButton: ImageButton  // Added swap button

    private val currencies = listOf(
        CurrencyItem("INR", "India", R.drawable.flag_india),
        CurrencyItem("USD", "USA", R.drawable.flag_usa),
        CurrencyItem("CAD", "Canada", R.drawable.flag_canada),
        CurrencyItem("JPY", "Japan", R.drawable.flag_japan),
        CurrencyItem("EUR", "Europe", R.drawable.flag_europe)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fromSpinner = findViewById(R.id.fromSpinner)
        toSpinner = findViewById(R.id.toSpinner)
        amountInput = findViewById(R.id.amountInput)
        resultText = findViewById(R.id.resultText)
        convertButton = findViewById(R.id.convertButton)
        swapButton = findViewById(R.id.swapButton)  // Initialize swap button

        val adapter = CurrencyAdapter(this, currencies)
        fromSpinner.adapter = adapter
        toSpinner.adapter = adapter

        convertButton.setOnClickListener {
            val fromCurrency = currencies[fromSpinner.selectedItemPosition].currencyCode
            val toCurrency = currencies[toSpinner.selectedItemPosition].currencyCode
            val amount = amountInput.text.toString().toDoubleOrNull() ?: 0.0

            val rate = getConversionRate(fromCurrency, toCurrency)
            val converted = rate * amount
            resultText.text = String.format("%.2f", converted)
        }

        swapButton.setOnClickListener {
            val fromPosition = fromSpinner.selectedItemPosition
            val toPosition = toSpinner.selectedItemPosition

            fromSpinner.setSelection(toPosition)
            toSpinner.setSelection(fromPosition)

            Toast.makeText(this, "Currencies swapped", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getConversionRate(from: String, to: String): Double {
        return when (from to to) {
            "USD" to "INR" -> 83.30
            "INR" to "USD" -> 1 / 83.30

            "EUR" to "INR" -> 90.50
            "INR" to "EUR" -> 1 / 90.50

            "JPY" to "INR" -> 0.55
            "INR" to "JPY" -> 1 / 0.55

            "CAD" to "INR" -> 61.70
            "INR" to "CAD" -> 1 / 61.70

            "USD" to "EUR" -> 0.93
            "EUR" to "USD" -> 1 / 0.93

            "USD" to "JPY" -> 151.00
            "JPY" to "USD" -> 1 / 151.00

            "USD" to "CAD" -> 1.35
            "CAD" to "USD" -> 1 / 1.35

            "EUR" to "JPY" -> 160.50
            "JPY" to "EUR" -> 1 / 160.50

            "EUR" to "CAD" -> 1.45
            "CAD" to "EUR" -> 1 / 1.45

            "CAD" to "JPY" -> 113.0
            "JPY" to "CAD" -> 1 / 113.0

            else -> 1.0 // Same currency or not matched
        }
    }
}
