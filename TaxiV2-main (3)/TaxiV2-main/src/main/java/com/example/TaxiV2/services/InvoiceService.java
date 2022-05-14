package com.example.TaxiV2.services;

import com.example.TaxiV2.models.Driver;
import com.example.TaxiV2.models.Invoice;
import com.example.TaxiV2.repositories.InvoiceRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService implements InvoiceManagement{
    private final InvoiceRepository invoiceRep;
    private final CustomerService customerService;
    private final DriverService driverService;

    public InvoiceService(InvoiceRepository invoiceRep, CustomerService customerService, DriverService driverService) {
        this.invoiceRep = invoiceRep;
        this.customerService = customerService;
        this.driverService = driverService;
    }

    @Override
    public List<Invoice> allInvoices() {
        return invoiceRep.findAll();
    }

    @Override
    public List<Invoice> filteredInvoice(LocalDate startDate, LocalDate endDate) {
        return invoiceRep.findAllByExtractedDateBetween(startDate,endDate);
    }

    @Override
    public List<Invoice> customerInvoice(Long customerId) {
        return invoiceRep.findAllByCustomer(customerService.getCustomer(customerId));
    }

    @Override
    public List<Invoice> driverInvoice(Long driverId) {
        return invoiceRep.findAllByDriver(driverService.getDriver(driverId));
    }


    @Override
    public Invoice insertInvoice(Invoice invoice) {
        invoiceRep.save(invoice);
        return invoice;
    }

    @Override
    public Invoice updateInvoice(Invoice invoice, long invoiceId) {
        Invoice newInvoice = getInvoice(invoiceId);
                newInvoice.setCustomer(invoice.getCustomer());
                newInvoice.setDriver(invoice.getDriver());
                newInvoice.setExtractedDate(invoice.getExtractedDate());
                newInvoice.setTotalPayment(invoice.getTotalPayment());
        return newInvoice;
    }

    @Override
    public Invoice getInvoice(Long invoiceId) {
        Optional<Invoice> invoiceOptional = invoiceRep.findById(invoiceId);
        if(invoiceOptional.isEmpty()){
            throw new IllegalStateException("Invoice with ID: "+invoiceId+" is not found");
        }
        else {
            return invoiceOptional.get();
        }
    }

    @Override
    public void deleteInvoice(Long invoiceId) {
        invoiceRep.deleteById(invoiceId);
    }

    public List<Invoice> getAllInvoice(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<Invoice> pagedResult = invoiceRep.findAll(paging);

        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<>();
        }
    }
}
