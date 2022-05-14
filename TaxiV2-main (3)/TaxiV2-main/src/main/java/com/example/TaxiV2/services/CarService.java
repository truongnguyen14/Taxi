package com.example.TaxiV2.services;

import com.example.TaxiV2.models.Car;
import com.example.TaxiV2.repositories.CarRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarService implements CarManagement {
    public final
    CarRepository carRep;

    public CarService(CarRepository carRep) {
        this.carRep = carRep;
    }

    @Override
    public List<Car> allCars() {
        return carRep.findAll(Sort.by(Sort.Direction.ASC,"carId"));
    }

    @Override
    public Car insertCar(Car car) {
        Optional<Car> carOptional = carRep.findByVIN(car.getVIN());
        if(carOptional.isPresent()){
            throw new IllegalStateException("VIN: "+ car.getVIN() +" already existed");
        }
        else {
            carRep.save(car);
            System.out.println(car);
            return car;
        }

    }

    @Override
    public Car updateCar(Car car, int VIN) {
        Car newCar = carRep.findByVIN(VIN).get();
            newCar.setCarId(newCar.getCarId());
            newCar.setDateCreated(ZonedDateTime.now());
            newCar.setVIN(car.getVIN());
            newCar.setManufacturer(car.getManufacturer());
            newCar.setModel(car.getModel());
            newCar.setColor(car.getColor());
            newCar.setConvertible(car.isConvertible());
            newCar.setRating(car.getRating());
            newCar.setLicensePlate(car.getLicensePlate());
            newCar.setPerKMRate(car.getPerKMRate());
            newCar.setTaken(car.isTaken());
            newCar.setAvailability(car.isAvailability());
            newCar.setDriverId(car.getCarId());
            carRep.save(newCar);
        return newCar;
    }

    @Override
    public void deleteCar(long carId) {
        boolean exists = carRep.existsById(carId);
        if(!exists){
            throw new IllegalStateException("Car with id: "+ carId+" does not exists");
        }
        else {
            carRep.deleteById(carId);
        }

    }

    @Override
    public Car getCar(long carId) {
        Optional<Car> carOptional = carRep.findById(carId);
            if(carOptional.isEmpty()){
                throw new IllegalStateException("Car with ID "+ carId + " is not found");
            }
            else{
                return carOptional.get();
            }
    }

    public List<Car> getAllCar(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<Car> pagedResult = carRep.findAll(paging);

        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<>();
        }
    }
}
