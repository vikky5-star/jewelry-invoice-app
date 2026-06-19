package com.jewelry.invoice.util

import android.content.Context
import android.os.Environment
import com.itextpdf.text.Document
import com.itextpdf.text.Element
import com.itextpdf.text.Font
import com.itextpdf.text.PageSize
import com.itextpdf.text.Paragraph
import com.itextpdf.text.Phrase
import com.itextpdf.text.Rectangle
import com.itextpdf.text.pdf.PdfPCell
import com.itextpdf.text.pdf.PdfPTable
import com.itextpdf.text.pdf.PdfWriter
import com.jewelry.invoice.data.entity.BusinessSettings
import com.jewelry.invoice.data.entity.Customer
import com.jewelry.invoice.data.entity.Invoice
import com.jewelry.invoice.data.entity.InvoiceItem
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class PDFGenerator(private val context: Context) {

    private val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    private val boldFont = Font(Font.FontFamily.HELVETICA, 14f, Font.BOLD)
    private val regularFont = Font(Font.FontFamily.HELVETICA, 11f)
    private val smallFont = Font(Font.FontFamily.HELVETICA, 9f)
    private val headerFont = Font(Font.FontFamily.HELVETICA, 10f, Font.BOLD)

    fun generateInvoicePDF(
        invoice: Invoice,
        customer: Customer,
        items: List<InvoiceItem>,
        businessSettings: BusinessSettings
    ): String? {
        return try {
            val fileName = "Invoice_${invoice.invoiceNumber}_${System.currentTimeMillis()}.pdf"
            val file = File(context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), fileName)
            file.parentFile?.mkdirs()

            val document = Document(PageSize.A4, 36f, 36f, 36f, 36f)
            PdfWriter.getInstance(document, FileOutputStream(file))
            document.open()

            // Header - Business Info
            addBusinessHeader(document, businessSettings)
            document.add(Paragraph(" "))

            // Invoice Title and Number
            addInvoiceTitle(document, invoice)
            document.add(Paragraph(" "))

            // Customer and Invoice Details
            addCustomerAndInvoiceDetails(document, customer, invoice)
            document.add(Paragraph(" "))

            // Items Table
            addItemsTable(document, items)
            document.add(Paragraph(" "))

            // Totals Section
            addTotalSection(document, invoice, items)
            document.add(Paragraph(" "))

            // Bank Details (if available)
            if (businessSettings.bankName.isNotEmpty()) {
                addBankDetails(document, businessSettings)
            }

            // Footer
            addFooter(document, businessSettings)

            document.close()
            file.absolutePath
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    private fun addBusinessHeader(document: Document, settings: BusinessSettings) {
        val headerTable = PdfPTable(2)
        headerTable.widthPercentage = 100f
        headerTable.setWidths(floatArrayOf(70f, 30f))

        val cell1 = PdfPCell(Phrase(settings.businessName, boldFont))
        cell1.border = Rectangle.NO_BORDER
        cell1.verticalAlignment = Element.ALIGN_LEFT
        headerTable.addCell(cell1)

        val cell2 = PdfPCell(Phrase("INVOICE", Font(Font.FontFamily.HELVETICA, 18f, Font.BOLD)))
        cell2.border = Rectangle.NO_BORDER
        cell2.horizontalAlignment = Element.ALIGN_RIGHT
        headerTable.addCell(cell2)

        document.add(headerTable)

        val addressPara = Paragraph()
        addressPara.add(Phrase("${settings.address}, ${settings.city}, ${settings.state} - ${settings.pincode}\n", smallFont))
        addressPara.add(Phrase("Phone: ${settings.phone} | Email: ${settings.email}\n", smallFont))
        addressPara.add(Phrase("GST: ${settings.gstNumber}", smallFont))
        addressPara.alignment = Element.ALIGN_LEFT
        document.add(addressPara)
    }

    private fun addInvoiceTitle(document: Document, invoice: Invoice) {
        val titleTable = PdfPTable(2)
        titleTable.widthPercentage = 100f

        val cell1 = PdfPCell(Phrase("Invoice No: ${invoice.invoiceNumber}", headerFont))
        cell1.border = Rectangle.NO_BORDER
        titleTable.addCell(cell1)

        val cell2 = PdfPCell(Phrase("Date: ${dateFormat.format(Date(invoice.invoiceDate))}", headerFont))
        cell2.border = Rectangle.NO_BORDER
        cell2.horizontalAlignment = Element.ALIGN_RIGHT
        titleTable.addCell(cell2)

        document.add(titleTable)
    }

    private fun addCustomerAndInvoiceDetails(document: Document, customer: Customer, invoice: Invoice) {
        val table = PdfPTable(2)
        table.widthPercentage = 100f

        // Bill To
        val billToPara = Paragraph()
        billToPara.add(Phrase("BILL TO:\n", Font(Font.FontFamily.HELVETICA, 10f, Font.BOLD)))
        billToPara.add(Phrase("${customer.name}\n", regularFont))
        billToPara.add(Phrase("${customer.address}\n", smallFont))
        billToPara.add(Phrase("${customer.city}, ${customer.state} - ${customer.pincode}\n", smallFont))
        billToPara.add(Phrase("Phone: ${customer.phone}", smallFont))
        val cell1 = PdfPCell(billToPara)
        cell1.border = Rectangle.NO_BORDER
        table.addCell(cell1)

        // Invoice Details
        val detailsPara = Paragraph()
        detailsPara.add(Phrase("INVOICE DETAILS:\n", Font(Font.FontFamily.HELVETICA, 10f, Font.BOLD)))
        detailsPara.add(Phrase("Invoice Date: ${dateFormat.format(Date(invoice.invoiceDate))}\n", smallFont))
        detailsPara.add(Phrase("Payment Status: ${invoice.paymentStatus}\n", smallFont))
        detailsPara.add(Phrase("Payment Method: ${invoice.paymentMethod}", smallFont))
        val cell2 = PdfPCell(detailsPara)
        cell2.border = Rectangle.NO_BORDER
        table.addCell(cell2)

        document.add(table)
    }

    private fun addItemsTable(document: Document, items: List<InvoiceItem>) {
        val table = PdfPTable(6)
        table.widthPercentage = 100f
        table.setWidths(floatArrayOf(10f, 25f, 20f, 15f, 15f, 15f))

        // Header
        val headers = arrayOf("S.No", "Description", "Details", "Qty", "Rate", "Amount")
        headers.forEachIndexed { index, header ->
            val cell = PdfPCell(Phrase(header, headerFont))
            cell.backgroundColor = com.itextpdf.text.BaseColor(220, 220, 220)
            cell.horizontalAlignment = Element.ALIGN_CENTER
            table.addCell(cell)
        }

        // Items
        items.forEachIndexed { index, item ->
            val details = "${item.metalType} ${item.purity}\nWt: ${item.weight}gm"

            val sNoCell = PdfPCell(Phrase("${index + 1}", regularFont))
            sNoCell.horizontalAlignment = Element.ALIGN_CENTER
            table.addCell(sNoCell)

            table.addCell(PdfPCell(Phrase(item.itemName, regularFont)))
            table.addCell(PdfPCell(Phrase(details, smallFont)))

            val qtyCell = PdfPCell(Phrase("${item.quantity}", regularFont))
            qtyCell.horizontalAlignment = Element.ALIGN_CENTER
            table.addCell(qtyCell)

            val rateCell = PdfPCell(Phrase("\u20b9${String.format("%.2f", item.unitPrice)}", regularFont))
            rateCell.horizontalAlignment = Element.ALIGN_RIGHT
            table.addCell(rateCell)

            val amountCell = PdfPCell(Phrase("\u20b9${String.format("%.2f", item.lineTotal)}", regularFont))
            amountCell.horizontalAlignment = Element.ALIGN_RIGHT
            table.addCell(amountCell)
        }

        document.add(table)
    }

    private fun addTotalSection(document: Document, invoice: Invoice, items: List<InvoiceItem>) {
        val table = PdfPTable(2)
        table.widthPercentage = 50f
        table.setWidths(floatArrayOf(50f, 50f))
        table.horizontalAlignment = Element.ALIGN_RIGHT

        // Subtotal
        val subtotalLabel = PdfPCell(Phrase("Subtotal:", regularFont))
        subtotalLabel.border = Rectangle.NO_BORDER
        subtotalLabel.horizontalAlignment = Element.ALIGN_RIGHT
        table.addCell(subtotalLabel)

        val subtotalValue = PdfPCell(Phrase("\u20b9${String.format("%.2f", invoice.subtotal)}", regularFont))
        subtotalValue.border = Rectangle.NO_BORDER
        subtotalValue.horizontalAlignment = Element.ALIGN_RIGHT
        table.addCell(subtotalValue)

        // GST
        val gstLabel = PdfPCell(Phrase("GST (${invoice.gstPercentage}%):", regularFont))
        gstLabel.border = Rectangle.NO_BORDER
        gstLabel.horizontalAlignment = Element.ALIGN_RIGHT
        table.addCell(gstLabel)

        val gstValue = PdfPCell(Phrase("\u20b9${String.format("%.2f", invoice.gstAmount)}", regularFont))
        gstValue.border = Rectangle.NO_BORDER
        gstValue.horizontalAlignment = Element.ALIGN_RIGHT
        table.addCell(gstValue)

        // Grand Total
        val grandTotalLabel = PdfPCell(Phrase("GRAND TOTAL:", boldFont))
        grandTotalLabel.border = Rectangle.NO_BORDER
        grandTotalLabel.horizontalAlignment = Element.ALIGN_RIGHT
        table.addCell(grandTotalLabel)

        val grandTotalValue = PdfPCell(Phrase("\u20b9${String.format("%.2f", invoice.grandTotal)}", boldFont))
        grandTotalValue.border = Rectangle.NO_BORDER
        grandTotalValue.horizontalAlignment = Element.ALIGN_RIGHT
        table.addCell(grandTotalValue)

        document.add(table)
    }

    private fun addBankDetails(document: Document, settings: BusinessSettings) {
        val bankPara = Paragraph()
        bankPara.add(Phrase("Bank Details:\n", Font(Font.FontFamily.HELVETICA, 10f, Font.BOLD)))
        bankPara.add(Phrase("Bank: ${settings.bankName}\n", smallFont))
        bankPara.add(Phrase("Account: ${settings.accountNumber}\n", smallFont))
        bankPara.add(Phrase("IFSC: ${settings.ifscCode}", smallFont))
        document.add(bankPara)
    }

    private fun addFooter(document: Document, settings: BusinessSettings) {
        val footerPara = Paragraph()
        footerPara.add(Phrase("\nThank you for your business!\n", Font(Font.FontFamily.HELVETICA, 10f, Font.BOLD)))
        footerPara.add(Phrase("For queries, contact: ${settings.phone}", smallFont))
        footerPara.alignment = Element.ALIGN_CENTER
        document.add(footerPara)
    }
}