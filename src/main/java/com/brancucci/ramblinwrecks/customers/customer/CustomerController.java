package com.brancucci.ramblinwrecks.customers.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController()
public class CustomerController {
    private CustomerService customerService;

    private static final String LOOKUP_CUSTOMER_URI = "/customer/lookup";
    private static final String ADD_CUSTOMER_URI = "/customer/add";

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping(path = LOOKUP_CUSTOMER_URI)
    public Mono<Customer> lookup(@RequestBody String driversLicense){
        return customerService.lookupCustomer(driversLicense);
    }

    @PostMapping(path = ADD_CUSTOMER_URI)
    public Mono<Customer> add(@RequestBody Customer customer) {
        return customerService.addCustomer(customer);
    }
}
