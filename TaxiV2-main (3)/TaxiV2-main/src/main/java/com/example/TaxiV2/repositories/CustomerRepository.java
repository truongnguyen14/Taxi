package com.example.TaxiV2.repositories;

import com.example.TaxiV2.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    @Query("select c from Customer c where c.IDNumber = ?1")
    Optional<Customer> findByIDNumber(int IDNumber);
}
