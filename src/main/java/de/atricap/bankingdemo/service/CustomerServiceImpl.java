package de.atricap.bankingdemo.service;

import de.atricap.bankingdemo.controller.CustomerNotFoundException;
import de.atricap.bankingdemo.entity.Customer;
import de.atricap.bankingdemo.entity.Phone;
import de.atricap.bankingdemo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> findById(int id) {
        return customerRepository.findById(id);
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    @Transactional
    public void update(Customer customer) throws CustomerNotFoundException {
        int id = customer.getId();
        Customer currentCustomer = customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException(id));

        currentCustomer.setFullName(customer.getFullName());
        currentCustomer.setEmail(customer.getEmail());
        currentCustomer.setBusinessCustomer(customer.isBusinessCustomer());
        currentCustomer.setUserName(customer.getUserName());
        currentCustomer.setEnabled(customer.isEnabled());
    }

    @Override
    public void deleteById(int id) {
        customerRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void addPhoneForCustomerById(int id, Phone phone) throws CustomerNotFoundException {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException(id));
        customer.addPhone(phone);
    }
}
