package com.example.TaxiV2.services;

import com.example.TaxiV2.models.Invoice;

import java.time.LocalDate;
import java.util.List;

public interface InvoiceManagement {
    List<Invoice> allInvoices();
    List<Invoice> filteredInvoice(LocalDate startDate, LocalDate endDate);
    List<Invoice> customerInvoice(Long customerId);
    List<Invoice> driverInvoice(Long driverId);
    Invoice insertInvoice(Invoice invoice);
    Invoice updateInvoice(Invoice invoice,long invoiceId);
    Invoice getInvoice(Long invoiceId);
    void deleteInvoice(Long invoiceId);
}
