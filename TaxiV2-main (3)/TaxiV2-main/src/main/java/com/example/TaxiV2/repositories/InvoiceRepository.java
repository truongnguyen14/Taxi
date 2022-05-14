package com.example.TaxiV2.repositories;

import com.example.TaxiV2.models.Customer;
import com.example.TaxiV2.models.Driver;
import com.example.TaxiV2.models.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    @Query("select i from Invoice i where i.extractedDate between ?1 and ?2")
    List<Invoice> findAllByExtractedDateBetween(LocalDate startDate, LocalDate endDate);
    @Query("select i from Invoice i where i.customer = ?1")
    List<Invoice> findAllByCustomer(Customer customer);
    @Query("select i from Invoice i where i.driver = ?1")
    List<Invoice> findAllByDriver(Driver driver);
}
