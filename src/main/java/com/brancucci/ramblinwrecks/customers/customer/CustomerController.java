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
    public Mono<Customer> lookup(@RequestBody Mono<String> driversLicense){
        return driversLicense.log().flatMap(dl -> customerService.lookupCustomer(dl));
    }

    @PostMapping(path = ADD_CUSTOMER_URI)
    public Mono<Customer> add(@RequestBody Mono<Customer> customer) {
        return customer.log().flatMap(cust -> customerService.addCustomer(cust));
    }
}