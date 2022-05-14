package com.example.TaxiV2.repositories;

import com.example.TaxiV2.models.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface CarRepository extends JpaRepository<Car,Long> {
    @Query("select c from Car c where c.VIN = ?1")
    Optional<Car> findByVIN(int VIN);
}
