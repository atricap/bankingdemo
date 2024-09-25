package de.atricap.bankingdemo.controller;

public class PhoneNotFoundException extends Exception {
    public PhoneNotFoundException(int customerId, int phoneId) {
        super("Cannot find phone %d for customer with id %d".formatted(phoneId, customerId));
    }
}
