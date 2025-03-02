package de.atricap.bankingdemo.customer;

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
