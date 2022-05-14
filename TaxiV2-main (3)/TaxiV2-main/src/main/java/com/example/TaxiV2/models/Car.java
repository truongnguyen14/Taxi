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
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long carId;

    private ZonedDateTime dateCreated = ZonedDateTime.now();
    private int VIN;
    private String manufacturer;
    private String model;
    private String color;
    private boolean convertible;
    private double rating;
    private String licensePlate;
    private double perKMRate;
    private boolean taken = false;
    private boolean Availability = true;
    private Long driverId;

    public Car(int VIN, String manufacturer, String model, String color, boolean convertible, double rating, String licensePlate, double perKMRate, boolean taken, boolean availability, Long driverId) {
        this.VIN = VIN;
        this.manufacturer = manufacturer;
        this.model = model;
        this.color = color;
        this.convertible = convertible;
        this.rating = rating;
        this.licensePlate = licensePlate;
        this.perKMRate = perKMRate;
        this.taken = taken;
        Availability = availability;
        this.driverId = driverId;
    }

    public Car(Long carId, ZonedDateTime dateCreated, int VIN, String manufacturer, String model, String color, boolean convertible, double rating, String licensePlate, double perKMRate, boolean taken, boolean availability, Long driverId) {
        this.carId = carId;
        this.dateCreated = dateCreated;
        this.VIN = VIN;
        this.manufacturer = manufacturer;
        this.model = model;
        this.color = color;
        this.convertible = convertible;
        this.rating = rating;
        this.licensePlate = licensePlate;
        this.perKMRate = perKMRate;
        this.taken = taken;
        Availability = availability;
        this.driverId = driverId;
    }
}
