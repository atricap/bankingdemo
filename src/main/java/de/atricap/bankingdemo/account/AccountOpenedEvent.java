package de.atricap.bankingdemo.account;

import org.springframework.context.ApplicationEvent;

import java.time.Clock;

public class AccountOpenedEvent extends ApplicationEvent {

    private final int customerId;
    private final Account account;

    public AccountOpenedEvent(Object source, Clock clock, int customerId, Account account) {
        super(source, clock);
        this.customerId = customerId;
        this.account = account;
    }

    public int getCustomerId() {
        return customerId;
    }

    public Account getAccount() {
        return account;
    }
}
