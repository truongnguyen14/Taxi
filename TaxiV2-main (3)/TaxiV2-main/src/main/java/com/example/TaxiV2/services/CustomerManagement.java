package com.example.TaxiV2.services;

import com.example.TaxiV2.models.Customer;

import java.util.List;

public interface CustomerManagement {
    Customer insertCustomer(Customer customer);

    Customer updateCustomer(Customer customer, int IDNumber);

    void deleteCustomer(long customerID);

    List<Customer> allCustomers();

    Customer getCustomer(long customerId);
}
