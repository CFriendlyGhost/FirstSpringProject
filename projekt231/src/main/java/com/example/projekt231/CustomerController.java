package com.example.projekt231;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping()
    public String getCustomer(Model model){
        List<Customer> customers = customerRepository.findAll();
        model.addAttribute("customers", customers);
        model.addAttribute("newCustomer", new Customer());
        return "customersList";
    }

    @PostMapping("/add")
    public String addCustomer(@ModelAttribute Customer customer){
        customerRepository.save(customer);
        System.out.println(customer);
        return "redirect:/customers";
    }

    @GetMapping("/add")
    public String addCustomerPage(@ModelAttribute Customer customer){
        return "addCustomer";
    }

    @PostMapping ("/{customerId}")
    public String deleteCustomer(@PathVariable("customerId") Integer id){
        customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("customer with id:" +
                        id + "does not exist"));

        customerRepository.deleteById(id);
        return "customersList";
    }

/*    @PutMapping("{customerId}") TODO
    public void updateCustomer(@PathVariable("customerId") Integer id,
                               @RequestBody Customer customerDetails){

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("customer with id:" +
                        id + "does not exist"));

        customer.setName(customerDetails.getName());
        customer.setAge(customerDetails.getAge());
        customer.setEmail(customerDetails.getEmail());

        customerRepository.save(customer);
    }*/
}
