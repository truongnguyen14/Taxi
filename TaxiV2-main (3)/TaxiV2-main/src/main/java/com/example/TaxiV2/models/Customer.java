package com.example.TaxiV2.models;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.ZonedDateTime;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    private ZonedDateTime dateCreated = ZonedDateTime.now();
    private String customerName;
    private LocalDate dateOfBirth;
    private int IDNumber;
    private boolean gender;

    public Customer(String customerName, LocalDate dateOfBirth, int IDNumber, boolean gender) {
        this.customerName = customerName;
        this.dateOfBirth = dateOfBirth;
        this.IDNumber = IDNumber;
        this.gender = gender;
    }
}
