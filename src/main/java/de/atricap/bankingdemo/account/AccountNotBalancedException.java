package de.atricap.bankingdemo.account;

public class AccountNotBalancedException extends Exception {

    private final int customerId;
    private final int accountNumber;

    public AccountNotBalancedException(int customerId, int accountNumber) {
        super("Account %d is not balanced, for customer with id %d".formatted(accountNumber, customerId));
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
