package de.atricap.bankingdemo.controller;

import de.atricap.bankingdemo.entity.Account;
import de.atricap.bankingdemo.entity.Customer;
import de.atricap.bankingdemo.entity.Phone;
import de.atricap.bankingdemo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/customers/{customerId}/accounts")
public class AccountController {

    CustomerService customerService;

    @Autowired
    public AccountController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{accountNumber}")
    public String showAccountDetails(@PathVariable int customerId, @PathVariable int accountNumber, Model model) throws CustomerNotFoundException, AccountNotFoundException {
        Customer customer = customerService.findById(customerId).orElseThrow(() -> new CustomerNotFoundException(customerId));
        Account account = customer.findAccount(accountNumber).orElseThrow(() -> new AccountNotFoundException(customerId, accountNumber));
        model.addAttribute("account", account);

        return "accounts/details";
    }
}
