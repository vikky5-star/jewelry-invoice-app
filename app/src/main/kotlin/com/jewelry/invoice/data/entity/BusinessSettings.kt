package com.jewelry.invoice.data.entity

data class BusinessSettings(
    val businessName: String = "My Jewelry Shop",
    val ownerName: String = "",
    val phone: String = "",
    val email: String = "",
    val address: String = "",
    val city: String = "",
    val state: String = "",
    val pincode: String = "",
    val gstNumber: String = "",
    val panNumber: String = "",
    val bankName: String = "",
    val accountNumber: String = "",
    val ifscCode: String = "",
    val invoicePrefix: String = "INV",
    val defaultGstRate: Double = 18.0,
    val logoUri: String? = null
)