package de.atricap.bankingdemo.service;

import de.atricap.bankingdemo.controller.CustomerNotFoundException;
import de.atricap.bankingdemo.controller.PhoneNotFoundException;
import de.atricap.bankingdemo.entity.Customer;
import de.atricap.bankingdemo.entity.Phone;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    List<Customer> findAll();

    Optional<Customer> findById(int id);

    Customer save(Customer customer);

    void update(Customer customer) throws CustomerNotFoundException;

    void deleteById(int id);

    void addPhoneForCustomerById(int id, Phone phone) throws CustomerNotFoundException;

    void deletePhoneForCustomerById(int customerId, long phoneNumber) throws CustomerNotFoundException, PhoneNotFoundException;
}
