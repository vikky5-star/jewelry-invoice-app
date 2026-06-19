package com.jewelry.invoice.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "invoices")
data class Invoice(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val invoiceNumber: String,
    val customerId: Int,
    val invoiceDate: Long = System.currentTimeMillis(),
    val subtotal: Double = 0.0,
    val gstPercentage: Double = 18.0, // 5%, 12%, 18%
    val gstAmount: Double = 0.0,
    val grandTotal: Double = 0.0,
    val paymentStatus: String = "Pending", // Pending, Paid, Partial
    val paymentMethod: String = "Cash",
    val notes: String = "",
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "invoice_items")
data class InvoiceItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val invoiceId: Int,
    val itemId: Int,
    val itemName: String,
    val quantity: Double,
    val unitPrice: Double,
    val lineTotal: Double = quantity * unitPrice,
    val metalType: String = "",
    val weight: Double = 0.0,
    val purity: String = ""
)