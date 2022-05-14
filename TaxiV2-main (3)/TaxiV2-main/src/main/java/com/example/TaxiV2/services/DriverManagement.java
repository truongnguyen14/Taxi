package com.example.TaxiV2.services;

import com.example.TaxiV2.models.Driver;

import java.util.List;

public interface DriverManagement {
    List<Driver> allDrivers();
    Driver insertDriver(Driver driver);
    Driver allocateCar(int licenseNumber,Long carID);
    Driver updateDriver(Driver driver, long driverID);
    void deleteDriver(long driverID);
    Driver getDriver(long driverID);

}
