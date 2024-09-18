package de.atricap.bankingdemo.service;

import de.atricap.bankingdemo.entity.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    List<Customer> findAll();

    Optional<Customer> findById(int id);

    Customer save(Customer customer);

    void deleteById(int id);
}
