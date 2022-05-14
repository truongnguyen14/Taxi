package com.example.TaxiV2.services;

import com.example.TaxiV2.models.Car;


import java.util.List;

public interface CarManagement {
    List<Car> allCars();
    Car insertCar(Car car);
    Car updateCar(Car car, int VIN);
    void deleteCar(long carId);
    Car getCar(long carId);

}
