package com.example.TaxiV2.models;

import lombok.*;


import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@NoArgsConstructor
@Getter
@Setter
@Entity
@ToString
@Table(name = "booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;

    private ZonedDateTime dateCreated = ZonedDateTime.now();

    @ManyToOne(targetEntity = Customer.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "customerId", referencedColumnName = "customerId")
    private Customer customer;


    @ManyToOne(targetEntity = Car.class,fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "carId", referencedColumnName = "carId")
    private Car car;

    private LocalDate bookedDate;
    private String startPoint;
    private String endPoint;




    private LocalDateTime pickUpTime;
    private LocalDateTime dropOffTime;

    private double distance;
    private double totalCharge;


    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "invoice_id", referencedColumnName = "invoiceId")
    private Invoice invoice;

    public Booking(Customer customer, Car car, LocalDate bookedDate, String startPoint, String endPoint, LocalDateTime pickUpTime, LocalDateTime dropOffTime, double distance, double totalCharge, Invoice invoice) {
        this.customer = customer;
        this.car = car;
        this.bookedDate = bookedDate;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.pickUpTime = pickUpTime;
        this.dropOffTime = dropOffTime;
        this.distance = distance;
        this.totalCharge = totalCharge;
        this.invoice = invoice;
    }

    public Booking(Long bookingId, ZonedDateTime dateCreated, Customer customer, Car car, LocalDate bookedDate, String startPoint, String endPoint, LocalDateTime pickUpTime, LocalDateTime dropOffTime, double distance, double totalCharge, Invoice invoice) {
        this.bookingId = bookingId;
        this.dateCreated = dateCreated;
        this.customer = customer;
        this.car = car;
        this.bookedDate = bookedDate;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.pickUpTime = pickUpTime;
        this.dropOffTime = dropOffTime;
        this.distance = distance;
        this.totalCharge = totalCharge;
        this.invoice = invoice;
    }

    public Booking(LocalDate bookedDate, String startPoint, String endPoint, LocalDateTime pickUpTime, LocalDateTime dropOffTime, double distance, double totalCharge, Invoice invoice) {
        this.bookedDate = bookedDate;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.pickUpTime = pickUpTime;
        this.dropOffTime = dropOffTime;
        this.distance = distance;
        this.totalCharge = totalCharge;
        this.invoice = invoice;
    }
}
