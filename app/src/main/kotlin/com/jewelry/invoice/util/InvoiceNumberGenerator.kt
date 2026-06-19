package com.jewelry.invoice.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object InvoiceNumberGenerator {
    fun generateInvoiceNumber(prefix: String = "INV", count: Int): String {
        val dateFormat = SimpleDateFormat("yyMMdd", Locale.getDefault())
        val dateString = dateFormat.format(Date())
        val serialNumber = String.format("%05d", count)
        return "$prefix-$dateString-$serialNumber"
    }

    fun generateNextInvoiceNumber(prefix: String, lastInvoiceNumber: String?, totalCount: Int): String {
        return generateInvoiceNumber(prefix, totalCount + 1)
    }
}