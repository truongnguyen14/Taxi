package com.example.TaxiV2.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.ZonedDateTime;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "invoice")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long invoiceId;


    private ZonedDateTime dateCreated = ZonedDateTime.now();

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "driver_id")
    private Driver driver;


    private LocalDate extractedDate;

    private double totalPayment;

    public Invoice(Customer customer, Driver driver, LocalDate extractedDate, double totalPayment) {
        this.customer = customer;
        this.driver = driver;
        this.extractedDate = extractedDate;
        this.totalPayment = totalPayment;
    }

    public Invoice(Long invoiceId, ZonedDateTime dateCreated, Customer customer, Driver driver, LocalDate extractedDate, double totalPayment) {
        this.invoiceId = invoiceId;
        this.dateCreated = dateCreated;
        this.customer = customer;
        this.driver = driver;
        this.extractedDate = extractedDate;
        this.totalPayment = totalPayment;
    }
}
