package de.atricap.bankingdemo.customer;

public class PhoneNotFoundException extends Exception {
    public PhoneNotFoundException(int customerId, long phoneNumber) {
        super("Cannot find phone %d for customer with id %d".formatted(phoneNumber, customerId));
    }
}
