package com.example.TaxiV2.controllers;

import com.example.TaxiV2.models.Car;
import com.example.TaxiV2.services.CarService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RequestMapping(path = "api/cars")
@RestController
public class CarController {
    private final CarService carService;


    @GetMapping(path = "getAllCars")
    public List<Car> getCars(){
        return carService.allCars();
    }

    @PostMapping(path = "createCar")
    public ResponseEntity<Car> createCar(@RequestBody Car car) {
        Car newCar = carService.insertCar(car);
        if(newCar == null){
            throw new IllegalStateException("Cannot insert Car");
        }
        else {
            return new ResponseEntity<>(newCar, HttpStatus.CREATED);
        }
    }

    @PutMapping("updateCar/{VIN}")
    public ResponseEntity<Car> updateCar(@PathVariable(value = "VIN") int VIN, @RequestBody Car car) {
        return new ResponseEntity<>(carService.updateCar(car,VIN), HttpStatus.OK);
    }
    @DeleteMapping(path = "deleteCar/{id}")
    public void deleteCar(@PathVariable(value = "id")Long carId){
        carService.deleteCar(carId);
    }

    @GetMapping(path = "page")
    public ResponseEntity<List<Car>> getAllCar(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "5") Integer pageSize,
            @RequestParam(defaultValue = "carId") String sortBy)
    {
        List<Car> list = carService.getAllCar(pageNo, pageSize, sortBy);

        return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
    }
}

