package com.example.TaxiV2.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.ZonedDateTime;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "driver")
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long driverId;

    private ZonedDateTime dateCreated = ZonedDateTime.now();

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "car_id", referencedColumnName = "carId")
    private Car car;

    private int licenseNumber;
    private int phoneNumber;
    private double rating;

    public Driver(Car car, int licenseNumber, int phoneNumber, double rating) {
        this.car = car;
        this.licenseNumber = licenseNumber;
        this.phoneNumber = phoneNumber;
        this.rating = rating;
    }
}
