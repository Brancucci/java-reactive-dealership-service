package com.brancucci.ramblinwrecks.customers.businesscustomer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class BusinessCustomerController {
    private BusinessCustomerService businessCustomerService;

    private static final String BUSINESS_CUSTOMER_LOOKUP_URI = "/business-customer/lookup";
    private static final String BUSINESS_CUSTOMER_ADD_URI = "/business-customer/add";


    @Autowired
    public BusinessCustomerController(BusinessCustomerService businessCustomerService) {
        this.businessCustomerService = businessCustomerService;
    }

    @PostMapping(path = BUSINESS_CUSTOMER_LOOKUP_URI)
    public Mono<BusinessCustomer> lookup(@RequestBody String taxId) {
        return businessCustomerService.lookupCustomer(taxId);
    }

    @PostMapping(path = BUSINESS_CUSTOMER_ADD_URI)
    public Mono<BusinessCustomer> add(@RequestBody BusinessCustomer customer) {
        return businessCustomerService.addCustomer(customer);
    }
}
