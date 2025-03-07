package de.atricap.bankingdemo.account;

import de.atricap.bankingdemo.customer.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/customers/{customerId}/accounts")
public class AccountController {

    CustomerService customerService;
    AccountService accountService;

    @Autowired
    public AccountController(
            CustomerService customerService,
            AccountService accountService
    ) {
        this.customerService = customerService;
        this.accountService = accountService;
    }

    @GetMapping("/{accountNumber}")
    public String showAccountDetails(@PathVariable int customerId, @PathVariable int accountNumber, Model model) throws CustomerNotFoundException, AccountNotFoundException {
        Customer customer = customerService.findById(customerId).orElseThrow(() -> new CustomerNotFoundException(customerId));
        Account account = customer.findAccount(accountNumber).orElseThrow(() -> new AccountNotFoundException(customerId, accountNumber));
        model.addAttribute("account", account);

        return "accounts/details";
    }

    @GetMapping("/open")
    public String showOpenAccount(@PathVariable int customerId, Model model) throws CustomerNotFoundException {
        Customer customer = customerService.findById(customerId).orElseThrow(() -> new CustomerNotFoundException(customerId));
        model.addAttribute("customer", customer);
        Account account = new Account();
        model.addAttribute("account", account);

        return "accounts/open";
    }

    @PostMapping("/open")
    public String postOpenAccount(@PathVariable int customerId, @ModelAttribute("account") Account account)
            throws CustomerNotFoundException, AccountNotBalancedException {
        accountService.openForCustomerById(customerId, account);

        return "redirect:/customers/%d".formatted(customerId);
    }

    @PostMapping("/{accountNumber}/close")
    public String postCloseAccount(@PathVariable int customerId, @PathVariable int accountNumber)
            throws CustomerNotFoundException, AccountNotFoundException, AccountNotBalancedException {
        accountService.closeForCustomerById(customerId, accountNumber);

        return "redirect:/customers/%d".formatted(customerId);
    }
}
