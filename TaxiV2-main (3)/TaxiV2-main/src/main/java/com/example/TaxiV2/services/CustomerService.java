package com.example.TaxiV2.services;

import com.example.TaxiV2.models.Car;
import com.example.TaxiV2.models.Customer;
import com.example.TaxiV2.repositories.CustomerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService implements CustomerManagement {
    public final
    CustomerRepository customerRep;

    public CustomerService(CustomerRepository customerRep) {
        this.customerRep = customerRep;
    }

    @Override
    public Customer insertCustomer(Customer customer) {
            customerRep.save(customer);
            return customer;
    }

    @Override
    public Customer updateCustomer(Customer customer, int IDNumber) {
        Customer newCustomer = customerRep.findByIDNumber(IDNumber).get();
                 newCustomer.setCustomerName(customer.getCustomerName());
                 newCustomer.setDateOfBirth(customer.getDateOfBirth());
                 newCustomer.setIDNumber(customer.getIDNumber());
                 newCustomer.setGender(customer.isGender());
                 customerRep.save(newCustomer);

                 return newCustomer;
    }

    @Override
    public void deleteCustomer(long customerID) {
        customerRep.deleteById(customerID);
    }

    @Override
    public List<Customer> allCustomers() {
        return customerRep.findAll(Sort.by(Sort.Direction.DESC,"customerId"));
    }

    @Override
    public Customer getCustomer(long customerId) {
        Optional<Customer> customerOptional = customerRep.findById(customerId);
            if(customerOptional.isEmpty()){
                throw new IllegalStateException("Customer with ID "+ customerId+ " is not found");
            }
            else {
                return customerOptional.get();
            }
    }

    public List<Customer> getAllCustomer(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<Customer> pagedResult = customerRep.findAll(paging);

        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<>();
        }
    }
}
