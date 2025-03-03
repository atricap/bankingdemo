package de.atricap.bankingdemo.customer;

import org.springframework.context.ApplicationEvent;

import java.time.Clock;

public class CustomerRemovedEvent extends ApplicationEvent {
    private int id;

    public CustomerRemovedEvent(Object source, Clock clock, int id) {
        super(source, clock);
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
