package de.atricap.bankingdemo.customer;

import org.springframework.context.ApplicationEvent;

import java.time.Clock;

public class CustomerUpdatedEvent extends ApplicationEvent {
    private Customer customer;

    public CustomerUpdatedEvent(Object source, Clock clock, Customer customer) {
        super(source, clock);
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }
}
