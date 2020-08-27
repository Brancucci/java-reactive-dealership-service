package com.brancucci.ramblinwrecks.customer;

import com.brancucci.ramblinwrecks.RamblinWrecksApplication;
import com.brancucci.ramblinwrecks.config.CassandraTestHelper;
import org.apache.thrift.transport.TTransportException;
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
public class CustomerRepositoryIT {

    @Autowired
    private CustomerRepository customerRepository;

    @BeforeClass
    public static void cassandra() throws InterruptedException, IOException, TTransportException {
        CassandraTestHelper.initEmbeddedCassandra();
    }

    @Test
    public void saveUser_success(){
        final String DL_NUMBER = "A1234";
        Customer customer = Customer.builder()
                .driversLicenseNumber(DL_NUMBER)
                .build();

        customerRepository.save(customer).block();

        StepVerifier.create(customerRepository.findById(DL_NUMBER))
                .expectNext(customer)
                .expectComplete()
                .verify();
    }
}