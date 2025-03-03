package de.atricap.bankingdemo.customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    List<Customer> findAll();

    Optional<Customer> findById(int id);

    void register(Customer customer);

    void update(Customer customer) throws CustomerNotFoundException;

    void removeById(int id) throws CustomerNotFoundException;

    void addPhoneForCustomerById(int id, Phone phone) throws CustomerNotFoundException;

    void deletePhoneForCustomerById(int customerId, long phoneNumber) throws CustomerNotFoundException, PhoneNotFoundException;
}
