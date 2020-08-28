package com.brancucci.ramblinwrecks.customers.customer;

import com.brancucci.ramblinwrecks.RamblinWrecksApplication;
import com.brancucci.ramblinwrecks.config.CassandraTestHelper;
import com.brancucci.ramblinwrecks.customers.State;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.transport.TTransportException;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.test.StepVerifier;
import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RamblinWrecksApplication.class})
@ActiveProfiles({"test"})
@Slf4j
public class CustomerRepositoryIT {

    @Autowired
    private CustomerRepository customerRepository;

    @BeforeClass
    public static void cassandra() throws InterruptedException, IOException, TTransportException {
        CassandraTestHelper.initEmbeddedCassandra();
    }

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

        customerRepository.save(customer).block();

        StepVerifier.create(customerRepository.findById(DL_NUMBER))
                .expectNext(customer)
                .verifyComplete();
    }

    @Test
    public void lookupCustomer_returnsEmpty(){
        final String DL_NUMBER = "A1234";

        StepVerifier.create(customerRepository.findById(DL_NUMBER))
                .expectComplete()
                .verify();
    }

    @After
    public void clearDatabase(){
        log.info("cleaning up DB");
        StepVerifier.create(customerRepository.deleteAll()).verifyComplete();
        log.info("cleaned up db");
    }
}
