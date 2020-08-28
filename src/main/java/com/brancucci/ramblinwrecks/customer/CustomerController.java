package com.brancucci.ramblinwrecks.customer;

import com.brancucci.ramblinwrecks.exception.UserAlreadyExists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController()
public class CustomerController {
    private CustomerService customerService;

    private static final String LOOKUP_CUSTOMER_URI = "/customer/lookup";

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping(path = LOOKUP_CUSTOMER_URI)
    public Mono<Customer> login(@RequestBody Mono<String> driversLicense){
        return driversLicense.log().flatMap(dl -> customerService.lookupCustomer(dl)
                .switchIfEmpty(Mono.error(new UserAlreadyExists(String.format("User %s already exists.", dl)))));
    }

}
