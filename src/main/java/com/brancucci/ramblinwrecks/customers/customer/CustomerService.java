package com.brancucci.ramblinwrecks.customers.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CustomerService {
    private CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Mono<Customer> lookupCustomer(String dl_number) {
        return customerRepository.findById(dl_number);
    }

    public Mono<Customer> addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }
}
