package com.example.TaxiV2.repositories;

import com.example.TaxiV2.models.Booking;
import com.example.TaxiV2.models.Car;
import com.example.TaxiV2.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface BookingRepository extends JpaRepository<Booking,Long> {
    @Query("select b from Booking b where b.bookedDate between ?1 and ?2")
    List<Booking> findAllByBookedDateBetween(LocalDate startDate, LocalDate endDate);


    @Query("select b from Booking b where b.customer = ?1 and b.car = ?2")
    Optional<Booking> findByCustomerAndCar(Customer customer, Car car);
}
