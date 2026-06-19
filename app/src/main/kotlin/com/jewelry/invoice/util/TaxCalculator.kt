package com.jewelry.invoice.util

object TaxCalculator {
    // Indian GST rates
    const val GST_5_PERCENT = 5.0
    const val GST_12_PERCENT = 12.0
    const val GST_18_PERCENT = 18.0

    fun calculateGST(subtotal: Double, gstRate: Double): Double {
        return subtotal * (gstRate / 100)
    }

    fun calculateGrandTotal(subtotal: Double, gstAmount: Double): Double {
        return subtotal + gstAmount
    }

    fun getGSTRateForCategory(category: String): Double {
        return when (category.lowercase()) {
            "plain_jewelry" -> GST_5_PERCENT
            "studded_jewelry" -> GST_12_PERCENT
            else -> GST_18_PERCENT // Default 18% for most jewelry items
        }
    }

    fun calculateLineTotal(quantity: Double, unitPrice: Double): Double {
        return quantity * unitPrice
    }

    fun calculateInvoiceTotal(items: List<Pair<Double, Double>>): Double {
        return items.sumOf { (quantity, price) -> quantity * price }
    }
}