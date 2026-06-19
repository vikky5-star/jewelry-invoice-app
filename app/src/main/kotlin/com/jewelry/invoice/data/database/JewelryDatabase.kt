package com.jewelry.invoice.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jewelry.invoice.data.dao.CustomerDao
import com.jewelry.invoice.data.dao.InvoiceDao
import com.jewelry.invoice.data.dao.JewelryItemDao
import com.jewelry.invoice.data.entity.Customer
import com.jewelry.invoice.data.entity.Invoice
import com.jewelry.invoice.data.entity.InvoiceItem
import com.jewelry.invoice.data.entity.JewelryItem

@Database(
    entities = [
        JewelryItem::class,
        Customer::class,
        Invoice::class,
        InvoiceItem::class
    ],
    version = 1,
    exportSchema = false
)
abstract class JewelryDatabase : RoomDatabase() {
    abstract fun jewelryItemDao(): JewelryItemDao
    abstract fun customerDao(): CustomerDao
    abstract fun invoiceDao(): InvoiceDao

    companion object {
        @Volatile
        private var instance: JewelryDatabase? = null

        fun getInstance(context: Context): JewelryDatabase {
            return instance ?: synchronized(this) {
                val db = Room.databaseBuilder(
                    context.applicationContext,
                    JewelryDatabase::class.java,
                    "jewelry_database"
                ).build()
                instance = db
                db
            }
        }
    }
}