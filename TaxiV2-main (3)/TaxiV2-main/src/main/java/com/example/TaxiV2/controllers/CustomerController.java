package com.example.TaxiV2.controllers;


import com.example.TaxiV2.models.Car;
import com.example.TaxiV2.models.Customer;
import com.example.TaxiV2.services.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RequestMapping(path = "api/customers")
@RestController
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping(path = "getAllCustomer")
    public List<Customer> getCustomers(){
        return customerService.allCustomers();
    }

    @PostMapping(path = "createCustomer")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        Customer newCustomer = customerService.insertCustomer(customer);
        if(newCustomer == null){
            throw new IllegalStateException("Cannot insert Customer");
        }
        else {
            return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
        }
    }

    @PutMapping(path = "updateCustomer/{IDNumber}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable(value = "IDNumber")int IDNumber,@RequestBody Customer customer) throws ConfigDataResourceNotFoundException {
        return new ResponseEntity<>(customerService.updateCustomer(customer,IDNumber), HttpStatus.OK);
    }

    @DeleteMapping(path = "deleteCustomer/{id}")
    public void deleteCustomer(@PathVariable(value="id")Long customerID){
        customerService.deleteCustomer(customerID);
    }

    @GetMapping(path = "page")
    public ResponseEntity<List<Customer>> getAllCustomer(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "5") Integer pageSize,
            @RequestParam(defaultValue = "customerId") String sortBy)
    {
        List<Customer> list = customerService.getAllCustomer(pageNo, pageSize, sortBy);

        return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
    }


}

