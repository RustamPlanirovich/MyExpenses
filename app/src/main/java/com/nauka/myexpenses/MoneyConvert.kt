package com.nauka.myexpenses

import java.text.NumberFormat
import java.util.*

class MoneyConvert  {

    fun moneyConvert(number: Int): String? {
        val num = number
        val format: NumberFormat = NumberFormat.getCurrencyInstance()
        val currency = format.format(num)
        return currency
    }

}