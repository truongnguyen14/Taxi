package com.example.TaxiV2.repositories;

import com.example.TaxiV2.models.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface DriverRepository extends JpaRepository<Driver, Long> {
    @Query("select d from Driver d where d.licenseNumber = ?1")
    Optional<Driver> findDriverByLicenseNumber(int licenseNumber);
}
