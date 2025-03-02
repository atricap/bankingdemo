package de.atricap.bankingdemo.controller;

public class AccountNotFoundException extends Exception {
    public AccountNotFoundException(int customerId, int accountNumber) {
        super("Cannot find phone %d for customer with id %d".formatted(accountNumber, customerId));
    }
}
