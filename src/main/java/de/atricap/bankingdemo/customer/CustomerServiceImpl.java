package de.atricap.bankingdemo.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;
    private Clock clock;
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    public CustomerServiceImpl(
            CustomerRepository customerRepository,
            Clock clock,
            ApplicationEventPublisher applicationEventPublisher
    ) {
        this.customerRepository = customerRepository;
        this.clock = clock;
        this.applicationEventPublisher = applicationEventPublisher;
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
    public void register(Customer customer) {
        applicationEventPublisher.publishEvent(
                new CustomerRegisteredEvent(this, clock, customer));
    }

    @EventListener
    void handleCustomerRegisteredEvent(CustomerRegisteredEvent event) {
        customerRepository.save(event.getCustomer());
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

    @Override
    @Transactional
    public void deletePhoneForCustomerById(int customerId, long phoneNumber) throws CustomerNotFoundException, PhoneNotFoundException {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new CustomerNotFoundException(customerId));
        Phone phone = customer.findPhone(phoneNumber).orElseThrow(() -> new PhoneNotFoundException(customerId, phoneNumber));
        customer.removePhone(phone);
    }
}
