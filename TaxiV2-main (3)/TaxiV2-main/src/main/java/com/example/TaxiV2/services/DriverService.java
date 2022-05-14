package com.example.TaxiV2.services;

import com.example.TaxiV2.models.Car;
import com.example.TaxiV2.models.Driver;
import com.example.TaxiV2.repositories.CarRepository;
import com.example.TaxiV2.repositories.DriverRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DriverService implements DriverManagement {

    public final DriverRepository driverRep;

    public final CarRepository carRep;

    public DriverService(DriverRepository driverRep, CarRepository carRep) {
        this.driverRep = driverRep;
        this.carRep = carRep;
    }


    @Override
    public List<Driver> allDrivers() {
        return driverRep.findAll(Sort.by(Sort.Direction.ASC,"driverId"));
    }

    @Override
    public Driver insertDriver(Driver driver) {
        driverRep.save(driver);
        return driver;
    }

    @Override
    public Driver allocateCar(int licenseNumber, Long carID) {
        Optional<Car> carOptional = carRep.findById(carID);
        Optional<Driver> driverOptional = driverRep.findDriverByLicenseNumber(licenseNumber);
        Driver newDriver;
        Car newCar;
        if (carOptional.isEmpty() || driverOptional.isEmpty()) {
            throw new IllegalStateException("Car with ID: " + carID + " or Driver with licenseNumber: "+ licenseNumber +" is not found");
        }  if(carOptional.get().isTaken()){
            throw new IllegalStateException("Car with ID: " + carID +" is taken");
        }  if(driverOptional.get().getCar() != null){
            throw new IllegalStateException("Driver with licenseNumber: " + licenseNumber +" already has a car");
        }  if(driverOptional.get().getCar() == carOptional.get()){
            throw new IllegalStateException("Car with ID: "+ carID+" is already been taken by Driver with licenseNumber: "+ licenseNumber);
        }
        else {
            newDriver = driverOptional.get();
            newCar = carOptional.get();
            newCar.setTaken(true);
            newCar.setDriverId(newDriver.getDriverId());
            newDriver.setCar(newCar);
            driverRep.save(newDriver);
            return newDriver;

        }

    }

    @Override
    public Driver updateDriver(Driver driver, long driverID) {
        Driver newDriver = getDriver(driverID);
               newDriver.setCar(driver.getCar());
               newDriver.setLicenseNumber(driver.getLicenseNumber());
               newDriver.setPhoneNumber(driver.getPhoneNumber());
               newDriver.setRating(driver.getRating());
               driverRep.save(newDriver);
               return newDriver;


    }

    @Override
    public void deleteDriver(long driverID) {
        driverRep.deleteById(driverID);
    }

    @Override
    public Driver getDriver(long driverID) {
        Optional<Driver> driverOptional = driverRep.findById(driverID);
            if(driverOptional.isEmpty()){
                throw new IllegalStateException("Driver with ID "+ driverID+ " is not found");
            }
            else {
                return driverOptional.get();
            }
    }

    public List<Driver> getAllDriver(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<Driver> pagedResult = driverRep.findAll(paging);

        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<>();
        }
    }
}
