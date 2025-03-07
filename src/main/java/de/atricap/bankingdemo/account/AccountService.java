package de.atricap.bankingdemo.account;

import de.atricap.bankingdemo.customer.CustomerNotFoundException;

public interface AccountService {

    void openForCustomerById(int customerId, Account account) throws CustomerNotFoundException, AccountNotBalancedException;

    void closeForCustomerById(int customerId, int accountNumber) throws CustomerNotFoundException, AccountNotFoundException, AccountNotBalancedException;
}
