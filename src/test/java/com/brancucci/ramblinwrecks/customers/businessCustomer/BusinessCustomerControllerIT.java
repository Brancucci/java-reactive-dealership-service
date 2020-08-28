package com.brancucci.ramblinwrecks.customers.businessCustomer;

import com.brancucci.ramblinwrecks.customers.State;
import com.brancucci.ramblinwrecks.customers.businesscustomer.BusinessCustomer;
import com.brancucci.ramblinwrecks.customers.businesscustomer.BusinessCustomerController;
import com.brancucci.ramblinwrecks.customers.businesscustomer.BusinessCustomerRepository;
import com.brancucci.ramblinwrecks.customers.businesscustomer.BusinessCustomerService;
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
@WebFluxTest(controllers = BusinessCustomerController.class)
@Import(BusinessCustomerService.class)
@ActiveProfiles({"test"})
public class BusinessCustomerControllerIT {
    @MockBean
    private BusinessCustomerRepository businessCustomerRepository;
    @Autowired
    private WebTestClient webClient;


    @Test
    public void lookupCustomer_returnsUser(){
        final String TAX_ID = "A1234";
        final String BUSINESS_CUSTOMER_LOOKUP_URI = "/business-customer/lookup";


        BusinessCustomer customer = BusinessCustomer.builder()
                .taxIdNumber(TAX_ID)
                .businessName("ABC Co")
                .primaryContactTitle("Mr.")
                .primaryContactName("John Smith")
                .street("1234 1st Street")
                .city("Tampa")
                .state(State.FL)
                .phone("1112223333")
                .zipCode("12345")
                .build();

        Mockito.when(businessCustomerRepository.findById(TAX_ID)).thenReturn(Mono.just(customer));


        webClient.post()
                .uri(BUSINESS_CUSTOMER_LOOKUP_URI)
                .body(BodyInserters.fromObject(TAX_ID))
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .jsonPath("$.taxIdNumber").isEqualTo(TAX_ID);
    }

    @Test
    public void lookupCustomer_returnsEmpty(){
        final String TAX_ID = "A1234";
        final String BUSINESS_CUSTOMER_LOOKUP_URI = "/business-customer/lookup";

        Mockito.when(businessCustomerRepository.findById(TAX_ID)).thenReturn(Mono.empty());

        webClient.post()
                .uri(BUSINESS_CUSTOMER_LOOKUP_URI)
                .body(BodyInserters.fromObject(TAX_ID))
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody().isEmpty();
    }

    @Test
    public void addCustomer_success(){
        final String TAX_ID = "A1234";
        final String BUSINESS_CUSTOMER_ADD_URI = "/business-customer/add";

        BusinessCustomer customer = BusinessCustomer.builder()
                .taxIdNumber(TAX_ID)
                .businessName("ABC Co")
                .primaryContactTitle("Mr.")
                .primaryContactName("John Smith")
                .street("1234 1st Street")
                .city("Tampa")
                .state(State.FL)
                .phone("1112223333")
                .zipCode("12345")
                .build();

        Mockito.when(businessCustomerRepository.save(customer)).thenReturn(Mono.just(customer));

        webClient.post()
                .uri(BUSINESS_CUSTOMER_ADD_URI)
                .body(BodyInserters.fromObject(customer))
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .jsonPath("$.taxIdNumber").isEqualTo(TAX_ID);
    }
}
