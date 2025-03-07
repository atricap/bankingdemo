package de.atricap.bankingdemo.account;

import org.springframework.context.ApplicationEvent;

import java.time.Clock;

public class AccountClosedEvent extends ApplicationEvent {

    private final int customerId;
    private final int accountNumber;

    public AccountClosedEvent(Object source, Clock clock, int customerId, int accountNumber) {
        super(source, clock);
        this.customerId = customerId;
        this.accountNumber = accountNumber;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getAccountNumber() {
        return accountNumber;
    }
}
