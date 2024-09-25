package de.atricap.bankingdemo.controller;

import de.atricap.bankingdemo.entity.Customer;
import de.atricap.bankingdemo.entity.Phone;
import de.atricap.bankingdemo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/add")
    public String showAddCustomer(Model model) {
        Customer customer = new Customer();
        model.addAttribute("customer", customer);

        return "customers/add";
    }

    @PostMapping("/add")
    public String postAddCustomer(@ModelAttribute("customer") Customer customer) {
        customer.setEnabled(true);
        customerService.save(customer);

        return "redirect:/customers/";
    }

    @GetMapping("/{id}/edit")
    public String showEditCustomer(@PathVariable int id, Model model) throws CustomerNotFoundException {
        Customer customer = customerService.findById(id).orElseThrow(() -> new CustomerNotFoundException(id));
        model.addAttribute("customer", customer);

        return "customers/edit";
    }

    @PostMapping("/{id}/edit")
    public String postEditCustomer(@PathVariable int id, @ModelAttribute("customer") Customer customer) throws CustomerNotFoundException {
        customerService.update(customer);

        return "redirect:/customers/";
    }


    @GetMapping("/{id}/phones/add")
    public String showAddPhone(@PathVariable int id, Model model) throws CustomerNotFoundException {
        Customer customer = customerService.findById(id).orElseThrow(() -> new CustomerNotFoundException(id));
        model.addAttribute("customer", customer);
        Phone phone = new Phone();
        model.addAttribute("phone", phone);

        return "customers/phones/add";
    }

    @PostMapping("/{id}/phones/add")
    public String postAddPhone(@PathVariable int id, @ModelAttribute("phone") Phone phone) throws CustomerNotFoundException {
        customerService.addPhoneForCustomerById(id, phone);

        return "redirect:/customers/%d/edit".formatted(id);
    }

    @PostMapping("/{customerId}/phones/{phoneId}/delete")
    public String postDeletePhone(@PathVariable int customerId, @PathVariable int phoneId) throws CustomerNotFoundException, PhoneNotFoundException {
        customerService.deletePhoneForCustomerById(customerId, phoneId);

        return "redirect:/customers/%d/edit".formatted(customerId);
    }
}
