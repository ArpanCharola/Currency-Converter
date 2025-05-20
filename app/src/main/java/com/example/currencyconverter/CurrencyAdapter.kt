package com.example.currencyconverter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import android.widget.ArrayAdapter

class CurrencyAdapter(
    context: Context,
    private val currencyList: List<CurrencyItem>
) : ArrayAdapter<CurrencyItem>(context, 0, currencyList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createView(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createView(position, convertView, parent)
    }

    private fun createView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.spinner_item, parent, false)
        val currency = currencyList[position]

        val imageView = view.findViewById<ImageView>(R.id.flagImageView)
        val textView = view.findViewById<TextView>(R.id.currencyTextView)

        imageView.setImageResource(currency.flagResId)
        textView.text = currency.currencyCode

        return view
    }
}
