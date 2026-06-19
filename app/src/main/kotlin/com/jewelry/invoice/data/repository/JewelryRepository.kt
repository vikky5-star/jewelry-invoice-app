package com.jewelry.invoice.data.repository

import com.jewelry.invoice.data.dao.CustomerDao
import com.jewelry.invoice.data.dao.InvoiceDao
import com.jewelry.invoice.data.dao.JewelryItemDao
import com.jewelry.invoice.data.entity.Customer
import com.jewelry.invoice.data.entity.Invoice
import com.jewelry.invoice.data.entity.InvoiceItem
import com.jewelry.invoice.data.entity.JewelryItem
import kotlinx.coroutines.flow.Flow

class JewelryRepository(
    private val jewelryItemDao: JewelryItemDao,
    private val customerDao: CustomerDao,
    private val invoiceDao: InvoiceDao
) {
    // Jewelry Items
    suspend fun addJewelryItem(item: JewelryItem) = jewelryItemDao.insert(item)
    suspend fun updateJewelryItem(item: JewelryItem) = jewelryItemDao.update(item)
    suspend fun deleteJewelryItem(item: JewelryItem) = jewelryItemDao.delete(item)
    fun getAllJewelryItems(): Flow<List<JewelryItem>> = jewelryItemDao.getAllItems()
    fun searchJewelryItems(query: String): Flow<List<JewelryItem>> = jewelryItemDao.searchItems(query)
    suspend fun getJewelryItemById(id: Int): JewelryItem? = jewelryItemDao.getItemById(id)

    // Customers
    suspend fun addCustomer(customer: Customer) = customerDao.insert(customer)
    suspend fun updateCustomer(customer: Customer) = customerDao.update(customer)
    suspend fun deleteCustomer(customer: Customer) = customerDao.delete(customer)
    fun getAllCustomers(): Flow<List<Customer>> = customerDao.getAllCustomers()
    fun searchCustomers(query: String): Flow<List<Customer>> = customerDao.searchCustomers(query)
    suspend fun getCustomerById(id: Int): Customer? = customerDao.getCustomerById(id)

    // Invoices
    suspend fun addInvoice(invoice: Invoice) = invoiceDao.insertInvoice(invoice)
    suspend fun addInvoiceItem(item: InvoiceItem) = invoiceDao.insertInvoiceItem(item)
    suspend fun updateInvoice(invoice: Invoice) = invoiceDao.updateInvoice(invoice)
    suspend fun deleteInvoice(invoice: Invoice) = invoiceDao.deleteInvoice(invoice)
    suspend fun deleteInvoiceItem(item: InvoiceItem) = invoiceDao.deleteInvoiceItem(item)
    fun getAllInvoices(): Flow<List<Invoice>> = invoiceDao.getAllInvoices()
    fun getInvoicesByCustomer(customerId: Int): Flow<List<Invoice>> = invoiceDao.getInvoicesByCustomer(customerId)
    suspend fun getInvoiceById(id: Int): Invoice? = invoiceDao.getInvoiceById(id)
    suspend fun getInvoiceItems(invoiceId: Int): List<InvoiceItem> = invoiceDao.getInvoiceItems(invoiceId)
    fun getInvoiceItemsFlow(invoiceId: Int): Flow<List<InvoiceItem>> = invoiceDao.getInvoiceItemsFlow(invoiceId)
    suspend fun getTotalInvoiceCount(): Int = invoiceDao.getTotalInvoiceCount()
    suspend fun getTotalRevenue(): Double? = invoiceDao.getTotalRevenue()
}