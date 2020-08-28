package com.brancucci.ramblinwrecks.customers.businesscustomer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class BusinessCustomerService {
    private BusinessCustomerRepository businessCustomerRepository;

    @Autowired
    public BusinessCustomerService(BusinessCustomerRepository businessCustomerRepository) {
        this.businessCustomerRepository = businessCustomerRepository;
    }


    public Mono<BusinessCustomer> lookupCustomer(String id) {
        return businessCustomerRepository.findById(id);
    }

    public Mono<BusinessCustomer> addCustomer(BusinessCustomer customer) {
        return businessCustomerRepository.save(customer);
    }
}
