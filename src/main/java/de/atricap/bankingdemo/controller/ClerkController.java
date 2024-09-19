package de.atricap.bankingdemo.controller;

import de.atricap.bankingdemo.entity.Customer;
import de.atricap.bankingdemo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/customers")
public class ClerkController {

    CustomerService customerService;

    @Autowired
    public ClerkController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/")
    public String showCustomers(Model model) {
        List<Customer> customers = customerService.findAll();
        model.addAttribute("customers", customers);

        return "customers/list";
    }

    @GetMapping("/{id}")
    public String showCustomerDetails(@PathVariable int id, Model model) throws CustomerNotFoundException {
        Customer customer = customerService.findById(id).orElseThrow(() -> new CustomerNotFoundException(id));
        model.addAttribute("customer", customer);

        return "customers/details";
    }
}
