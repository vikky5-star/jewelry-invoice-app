package com.jewelry.invoice.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "jewelry_items")
data class JewelryItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val itemCode: String,
    val itemName: String,
    val category: String, // Ring, Necklace, Earring, Bracelet, etc.
    val metalType: String, // Gold, Silver, Platinum
    val purity: String, // 18K, 22K, 925, etc.
    val weight: Double, // in grams
    val description: String,
    val price: Double,
    val imageUri: String? = null,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)