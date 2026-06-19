package com.jewelry.invoice.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.jewelry.invoice.data.entity.Invoice
import com.jewelry.invoice.data.entity.InvoiceItem
import kotlinx.coroutines.flow.Flow

@Dao
interface InvoiceDao {
    @Insert
    suspend fun insertInvoice(invoice: Invoice): Long

    @Insert
    suspend fun insertInvoiceItem(item: InvoiceItem): Long

    @Update
    suspend fun updateInvoice(invoice: Invoice)

    @Delete
    suspend fun deleteInvoice(invoice: Invoice)

    @Delete
    suspend fun deleteInvoiceItem(item: InvoiceItem)

    @Query("SELECT * FROM invoices WHERE id = :id")
    suspend fun getInvoiceById(id: Int): Invoice?

    @Query("SELECT * FROM invoices ORDER BY invoiceDate DESC")
    fun getAllInvoices(): Flow<List<Invoice>>

    @Query("SELECT * FROM invoices WHERE customerId = :customerId ORDER BY invoiceDate DESC")
    fun getInvoicesByCustomer(customerId: Int): Flow<List<Invoice>>

    @Query("SELECT * FROM invoice_items WHERE invoiceId = :invoiceId")
    suspend fun getInvoiceItems(invoiceId: Int): List<InvoiceItem>

    @Query("SELECT * FROM invoice_items WHERE invoiceId = :invoiceId")
    fun getInvoiceItemsFlow(invoiceId: Int): Flow<List<InvoiceItem>>

    @Query("SELECT COUNT(*) FROM invoices")
    suspend fun getTotalInvoiceCount(): Int

    @Query("SELECT SUM(grandTotal) FROM invoices")
    suspend fun getTotalRevenue(): Double?
}