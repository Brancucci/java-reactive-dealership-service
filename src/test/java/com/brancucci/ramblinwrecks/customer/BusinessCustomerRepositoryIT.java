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
public class BusinessCustomerRepositoryIT {
    @Autowired
    private BusinessCustomerRepository businessCustomerRepository;

    @BeforeClass
    public static void cassandra() throws InterruptedException, IOException, TTransportException {
        CassandraTestHelper.initEmbeddedCassandra();
    }

    @Test
    public void saveBusinessUser_success(){
        final String TAX_ID_NUMBER = "A1234";

        BusinessCustomer businessCustomer = BusinessCustomer.builder()
                .taxIdNumber(TAX_ID_NUMBER)
                .build();

        businessCustomerRepository.save(businessCustomer).block();

        StepVerifier.create(businessCustomerRepository.findById(TAX_ID_NUMBER))
                .expectNext(businessCustomer)
                .expectComplete()
                .verify();
    }
}
