package de.atricap.bankingdemo.account;

import de.atricap.bankingdemo.customer.Customer;
import de.atricap.bankingdemo.customer.CustomerNotFoundException;
import de.atricap.bankingdemo.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Clock;

@Service
public class AccountServiceImpl implements AccountService {

    private CustomerService customerService;
    private Clock clock;
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    public AccountServiceImpl(
            CustomerService customerService,
            Clock clock,
            ApplicationEventPublisher applicationEventPublisher
    ) {
        this.customerService = customerService;
        this.clock = clock;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    @Transactional
    public void openForCustomerById(int customerId, Account account) throws CustomerNotFoundException, AccountNotBalancedException {
        Customer customer = customerService.findById(customerId).orElseThrow(() -> new CustomerNotFoundException(customerId));
        int accountNumber = account.getNumber();
        BigDecimal balance = account.getBalance();

        if (balance == null) {
            balance = BigDecimal.ZERO;
            account.setBalance(balance);
        }
        final BigDecimal ZERO = BigDecimal.ZERO.setScale(balance.scale());
        if (!ZERO.equals(balance)) {
            throw new AccountNotBalancedException(customerId, accountNumber);
        }

        applicationEventPublisher.publishEvent(
                new AccountOpenedEvent(this, clock, customerId, account));
    }

    @EventListener
    void handleAccountOpenedEvent(AccountOpenedEvent event) throws CustomerNotFoundException {
        int customerId = event.getCustomerId();
        Customer customer =  customerService.findById(customerId).orElseThrow(() -> new CustomerNotFoundException(customerId));
        Account account = event.getAccount();

        customer.addAccount(account);
    }

    @Override
    @Transactional
    public void closeForCustomerById(int customerId, int accountNumber) throws CustomerNotFoundException, AccountNotFoundException, AccountNotBalancedException {
        Customer customer = customerService.findById(customerId).orElseThrow(() -> new CustomerNotFoundException(customerId));
        Account account = customer.findAccount(accountNumber).orElseThrow(() -> new AccountNotFoundException(customerId, accountNumber));

        final BigDecimal balance = account.getBalance();
        final BigDecimal ZERO = BigDecimal.ZERO.setScale(balance.scale());
        if (!ZERO.equals(balance)) {
            throw new AccountNotBalancedException(customerId, accountNumber);
        }

        applicationEventPublisher.publishEvent(
                new AccountClosedEvent(this, clock, customerId, accountNumber));
    }

    @EventListener
    void handleAccountClosedEvent(AccountClosedEvent event) throws CustomerNotFoundException, AccountNotFoundException {
        int customerId = event.getCustomerId();
        Customer customer =  customerService.findById(customerId).orElseThrow(() -> new CustomerNotFoundException(customerId));
        int accountNumber = event.getAccountNumber();
        Account account = customer.findAccount(accountNumber).orElseThrow(() -> new AccountNotFoundException(customerId, accountNumber));

        customer.removeAccount(account);
    }
}
