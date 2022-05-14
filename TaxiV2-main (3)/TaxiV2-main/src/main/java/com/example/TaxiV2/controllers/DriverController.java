package com.example.TaxiV2.controllers;


import com.example.TaxiV2.models.Car;
import com.example.TaxiV2.models.Driver;
import com.example.TaxiV2.services.CarService;
import com.example.TaxiV2.services.DriverService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RequestMapping(path = "api/drivers")
@RestController
public class DriverController {
    private final DriverService driverService;
    private final CarService carService;

    @GetMapping(path = "getAllDriver")
    public List<Driver> getDrivers(){
        return driverService.allDrivers();
    }

    @PostMapping(path = "createDriver")
    public ResponseEntity<Driver> createDriver(@RequestBody Driver driver) {
        Driver newDriver = driverService.insertDriver(driver);
        if(newDriver == null){
            throw new IllegalStateException("Cannot insert Driver");
        }
        else {
            return new ResponseEntity<>(newDriver, HttpStatus.CREATED);
        }
    }

    @PutMapping(path = "{car_Id}/{licenseNumber}")
    public ResponseEntity<Driver> insertCar(@PathVariable(value = "car_Id")Long carId,@PathVariable(value = "licenseNumber") int licenseNumber){
        return new ResponseEntity<>(driverService.allocateCar(licenseNumber,carId), HttpStatus.OK);
    }

    @PutMapping(path="updateDriver/{driver_id}")
    public ResponseEntity<Driver> updateDriver(@PathVariable(value = "driver_id")Long driverId, @RequestBody Driver driver){
        return new ResponseEntity<>(driverService.updateDriver(driver,driverId), HttpStatus.OK);
    }

    @DeleteMapping(path = "deleteDriver/{id}")
    public void deleteDriver(@PathVariable(value="id")Long driverID){
        driverService.deleteDriver(driverID);
    }

    @GetMapping(path = "page")
    public ResponseEntity<List<Driver>> getAllDriver(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "5") Integer pageSize,
            @RequestParam(defaultValue = "driverId") String sortBy)
    {
        List<Driver> list = driverService.getAllDriver(pageNo, pageSize, sortBy);

        return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
    }


}
