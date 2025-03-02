package de.atricap.bankingdemo.controller;

public class PhoneNotFoundException extends Exception {
    public PhoneNotFoundException(int customerId, long phoneNumber) {
        super("Cannot find phone %d for customer with id %d".formatted(phoneNumber, customerId));
    }
}
