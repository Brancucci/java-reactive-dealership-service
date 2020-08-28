package com.brancucci.ramblinwrecks.customer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    @BeforeEach
    public void setup(){
        customerService = new CustomerService(customerRepository);
    }

    @Test
    public void checkIfCustomerExists_returnsFalse(){
        final String DL_NUMBER = "A1234";

        when(customerRepository.findById(DL_NUMBER)).thenReturn(Mono.empty());

        StepVerifier.create(customerService.lookupCustomer(DL_NUMBER))
                .expectComplete()
                .verify();
    }

    @Test
    public void lookupCustomer_returnsTrue(){
        final String DL_NUMBER = "A1234";

        Customer customer = Customer.builder()
                .driversLicenseNumber(DL_NUMBER)
                .firstName("John")
                .lastName("Smith")
                .street("1234 1st Street")
                .city("Tampa")
                .state(State.FL)
                .email("abc@gmail.com")
                .phone("1112223333")
                .zipCode("12345")
                .build();

        when(customerRepository.findById(DL_NUMBER)).thenReturn(Mono.just(customer));

        StepVerifier.create(customerService.lookupCustomer(DL_NUMBER))
                .expectNext(customer)
                .verifyComplete();
    }

    @Test
    public void addCustomer(){
        final String DL_NUMBER = "A1234";

        Customer customer = Customer.builder()
                .driversLicenseNumber(DL_NUMBER)
                .firstName("John")
                .lastName("Smith")
                .street("1234 1st Street")
                .city("Tampa")
                .state(State.FL)
                .email("abc@gmail.com")
                .phone("1112223333")
                .zipCode("12345")
                .build();

        when(customerRepository.save(customer)).thenReturn(Mono.just(customer));

        StepVerifier.create(customerService.addCustomer(customer))
                .expectNext(customer)
                .verifyComplete();
    }
    // todo add expection handling for lookup and add customers - reference Twitter strategy

}
