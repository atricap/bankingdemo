package de.atricap.bankingdemo.controller;

public class CustomerNotFoundException extends Exception {
    public CustomerNotFoundException(int id) {
        super("Cannot find customer for id %d".formatted(id));
    }
}
