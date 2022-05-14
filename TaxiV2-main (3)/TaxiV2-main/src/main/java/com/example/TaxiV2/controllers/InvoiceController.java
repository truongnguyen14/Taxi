package com.example.TaxiV2.controllers;


import com.example.TaxiV2.models.Car;
import com.example.TaxiV2.models.Invoice;
import com.example.TaxiV2.services.InvoiceService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@RequestMapping(path = "api/invoices")
@RestController
public class InvoiceController {
    private final InvoiceService invoiceService;

    @GetMapping(path = "getAllInvoice")
    public List<Invoice> getInvoices(){
        return invoiceService.allInvoices();
    }

    @GetMapping(path = "getAllInvoicesBetweenDates")
    public List<Invoice> findInvoicesBetween(@RequestParam(name = "startDate")String startDate,@RequestParam(name = "endDate") String endDate){
        LocalDate beginningDate = LocalDate.parse(startDate);
        LocalDate endingDate = LocalDate.parse(endDate);
        return invoiceService.filteredInvoice(beginningDate,endingDate);
    }

    @GetMapping(path = "getRevenue")
    public double getRevenue(@RequestParam(name = "startDate")String startDate, @RequestParam(name = "endDate") String endDate){
        int position = 0;
        double revenue = 0;
        List<Invoice> revenueList = findInvoicesBetween(startDate,endDate);
        while (position < revenueList.size()){
            revenue = revenue + revenueList.get(position).getTotalPayment();
            position++;
        }
        System.out.println(revenue);
        return revenue;
    }

    @GetMapping(path = "getRevenueByCustomer")
    public double getRevenueByCustomer(@RequestParam(name = "customerId")Long customerId,@RequestParam(name = "startDate")String startDate, @RequestParam(name = "endDate") String endDate){
        int position1 = 0;
        int position2 = 0;
        double revenue = 0;
        LocalDate beginningDate = LocalDate.parse(startDate);
        LocalDate endingDate = LocalDate.parse(endDate);
        List<Invoice> invoicesOfCustomer = invoiceService.customerInvoice(customerId);
        List<Invoice> invoicesByDate = new ArrayList<>();
        while (position1 < invoicesOfCustomer.size()){
            if(
                    (invoicesOfCustomer.get(position1).getExtractedDate().compareTo(beginningDate) >= 0) &&
                            (invoicesOfCustomer.get(position1).getExtractedDate().compareTo(endingDate) <=0 )
            ){
                invoicesByDate.add(invoicesOfCustomer.get(position1));
            }
            position1++;
        }
        while (position2 < invoicesByDate.size()){
            revenue = revenue + invoicesByDate.get(position2).getTotalPayment();
            position2++;
        }
        return revenue;
    }

    @GetMapping(path = "getRevenueByDriver")
    public double getRevenueByDriver(@RequestParam(name = "driverId")Long driverId,@RequestParam(name = "startDate")String startDate, @RequestParam(name = "endDate") String endDate){
        int position1 = 0;
        int position2 = 0;
        double revenue = 0;
        LocalDate beginningDate = LocalDate.parse(startDate);
        LocalDate endingDate = LocalDate.parse(endDate);
        List<Invoice> invoicesOfDriver = invoiceService.driverInvoice(driverId);
        List<Invoice> invoicesDriverByDate = new ArrayList<>();
        while (position1 < invoicesOfDriver.size()){
            if(
                    (invoicesOfDriver.get(position1).getExtractedDate().compareTo(beginningDate) >= 0) &&
                            (invoicesOfDriver.get(position1).getExtractedDate().compareTo(endingDate) <=0 )
            ){
                invoicesDriverByDate.add(invoicesOfDriver.get(position1));
            }
            position1++;
        }
        while (position2 < invoicesDriverByDate.size()){
            revenue = revenue + invoicesDriverByDate.get(position2).getTotalPayment();
            position2++;
        }
        return revenue;

    }



    @PostMapping(path = "createInvoice")
    public ResponseEntity<Invoice> createInvoice(@RequestBody Invoice invoice){
        Invoice newInvoice = invoiceService.insertInvoice(invoice);
        if(newInvoice == null){
            throw new IllegalStateException("Cannot insert invoice");
        }
        else {
            return new ResponseEntity<>(newInvoice,HttpStatus.CREATED);
        }
    }

    @PutMapping(path = "updateInvoice/{id}")
    public ResponseEntity<Invoice> updateInvoice(@PathVariable(value = "id")Long invoiceId, @RequestBody Invoice invoice){
        return new ResponseEntity<>(invoiceService.updateInvoice(invoice,invoiceId), HttpStatus.OK);
    }

    @DeleteMapping(path = "deleteInvoice/{id}")
    public void deleteInvoice(@PathVariable(value = "id")Long invoiceId){
        invoiceService.deleteInvoice(invoiceId);
    }

    @GetMapping(path = "page")
    public ResponseEntity<List<Invoice>> getAllInvoice(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "5") Integer pageSize,
            @RequestParam(defaultValue = "carId") String sortBy)
    {
        List<Invoice> list = invoiceService.getAllInvoice(pageNo,pageSize,sortBy);

        return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
    }
}
