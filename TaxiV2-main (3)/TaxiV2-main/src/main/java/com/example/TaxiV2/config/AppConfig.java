package com.example.TaxiV2.config;

import com.example.TaxiV2.models.Car;
import com.example.TaxiV2.models.Customer;
import com.example.TaxiV2.models.Driver;
import com.example.TaxiV2.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Component
@Configuration
public class AppConfig implements CommandLineRunner {
    private final BookingRepository bookingRepository;
    private final CarRepository carRepository;
    private final CustomerRepository customerRepository;
    private final DriverRepository driverRepository;
    private final InvoiceRepository invoiceRepository;

    public AppConfig(BookingRepository bookingRepository, CarRepository carRepository, CustomerRepository customerRepository, DriverRepository driverRepository, InvoiceRepository invoiceRepository) {
        this.bookingRepository = bookingRepository;
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
        this.driverRepository = driverRepository;
        this.invoiceRepository = invoiceRepository;
    }


    @Override
    public void run(String... args) {

        Car car1 = new Car(
                11,
                "dfdfdfdfdfd",
                "fghfghfghfgh",
                "blue",
                false,
                6,
                "ffffffff",
                9,
                false,
                false,
                null
        );
        Car car2 = new Car(
                12,
                "asdasdasdsadas",
                "wwwwwwww",
                "green",
                false,
                7,
                "fffffffffff",
                9,
                false,
                false,
                null
        );
        Car car3 = new Car(
                13,
                "asdasdasdsadas",
                "wwwwwwww",
                "red",
                false,
                7,
                "fffffffffff",
                9,
                false,
                false,
                null
        );
        Car car4 = new Car(
                14,
                "asdasdasdsadas",
                "wwwwwwww",
                "blue",
                false,
                7,
                "fffffffffff",
                9,
                false,
                false,
                null
        );
        Car car5 = new Car(
                15,
                "asdasdasdsadas",
                "wwwwwwww",
                "orange",
                false,
                7,
                "fffffffffff",
                9,
                false,
                false,
                null
        );
        Car car6 = new Car(
                16,
                "asdasdasdsadas",
                "wwwwwwww",
                "deep blue",
                false,
                7,
                "fffffffffff",
                9,
                false,
                false,
                null
        );
        Car car7 = new Car(
                17,
                "asdasdasdsadas",
                "wwwwwwww",
                "pink",
                false,
                7,
                "fffffffffff",
                9,
                false,
                false,
                null
        );
        Car car8 = new Car(
                18,
                "asdasdasdsadas",
                "wwwwwwww",
                "silver",
                false,
                7,
                "fffffffffff",
                9,
                false,
                false,
                null
        );
        Car car9 = new Car(
                19,
                "asdasdasdsadas",
                "wwwwwwww",
                "black",
                false,
                7,
                "fffffffffff",
                9,
                false,
                false,
                null
        );
        carRepository.saveAll(List.of(car1, car2, car3, car4, car5, car6, car7, car8, car9));
        Driver driver1 = new Driver(
                null,
                12345,
                999999999,
                9.0
        );
        Driver driver2 = new Driver(
                null,
                67891,
                888888888,
                8.0
        );
        Driver driver3 = new Driver(
                null,
                11112,
                123478998,
                6.0
        );
        Driver driver4 = new Driver(
                null,
                12478,
                134897987,
                8.0
        );
        Driver driver5 = new Driver(
                null,
                14698,
                789413377,
                4.0
        );
        Driver driver6 = new Driver(
                null,
                98767,
                786169979,
                8.0
        );
        Driver driver7 = new Driver(
                null,
                78946,
                123499789,
                9.0
        );
        Driver driver8 = new Driver(
                null,
                48976,
                898423648,
                6.0
        );
        Driver driver9 = new Driver(
                null,
                59873,
                569734787,
                7.0
        );
        driverRepository.saveAll(List.of(driver1, driver2, driver3, driver4, driver5, driver6, driver7, driver8, driver9));

        Customer customer1 = new Customer(
                "Truong",
                LocalDate.of(2000, Month.JANUARY, 1),
                123,
                true
        );
        Customer customer2 = new Customer(
                "Duong",
                LocalDate.of(2000, Month.FEBRUARY, 2),
                456,
                true
        );
        Customer customer3 = new Customer(
                "Ho",
                LocalDate.of(2002, Month.JANUARY, 11),
                789,
                true);
        Customer customer4 = new Customer(
                "Hai",
                LocalDate.of(2010, Month.MARCH, 20),
                101,
                true
        );
        Customer customer5 = new Customer(
                "Peter",
                LocalDate.of(1999, Month.APRIL, 1),
                112,
                true
        );
        Customer customer6 = new Customer(
                "Parker",
                LocalDate.of(2000, Month.JUNE, 1),
                131,
                true
        );
        Customer customer7 = new Customer(
                "May",
                LocalDate.of(1989, Month.MAY, 5),
                415,
                true
        );
        Customer customer8 = new Customer(
                "Bill",
                LocalDate.of(1972, Month.AUGUST, 25),
                161,
                true
        );
        customerRepository.saveAll(List.of(customer1, customer2, customer3, customer4, customer5, customer6, customer7, customer8));

    }



}
