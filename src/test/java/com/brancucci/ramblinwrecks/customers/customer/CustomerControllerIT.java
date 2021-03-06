package com.brancucci.ramblinwrecks.customers.customer;

import com.brancucci.ramblinwrecks.customers.State;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Mono;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = CustomerController.class)
@Import(CustomerService.class)
@ActiveProfiles({"test"})
public class CustomerControllerIT {
    @MockBean
    private CustomerRepository customerRepository;
    private static final String LOOKUP_CUSTOMER_URI = "/customer/lookup";


    @Autowired
    private WebTestClient webClient;

    @Test
    public void lookupCustomer_returnsUser(){
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

        Mockito.when(customerRepository.findById(DL_NUMBER)).thenReturn(Mono.just(customer));


        webClient.post()
                .uri(LOOKUP_CUSTOMER_URI)
                .body(BodyInserters.fromObject(DL_NUMBER))
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .jsonPath("$.driversLicenseNumber").isEqualTo(DL_NUMBER);
    }

    @Test
    public void lookupCustomer_returnsEmpty(){
        final String DL_NUMBER = "A1234";

        Mockito.when(customerRepository.findById(DL_NUMBER)).thenReturn(Mono.empty());


        webClient.post()
                .uri("/customer/lookup")
                .body(BodyInserters.fromObject(DL_NUMBER))
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody().isEmpty();
    }

    @Test
    public void addCustomer_success(){
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

        Mockito.when(customerRepository.save(customer)).thenReturn(Mono.just(customer));


        webClient.post()
                .uri("/customer/add")
                .body(BodyInserters.fromObject(customer))
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .jsonPath("$.driversLicenseNumber").isEqualTo(DL_NUMBER);
    }
}
