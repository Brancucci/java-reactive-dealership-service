package com.brancucci.ramblinwrecks.login;

import com.brancucci.ramblinwrecks.RamblinWrecksApplication;
import com.brancucci.ramblinwrecks.config.CassandraTestHelper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.thrift.transport.TTransportException;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RamblinWrecksApplication.class})
@ActiveProfiles({"test"})
public class LoginRepositoryIT {
    private static final Log LOGGER = LogFactory.getLog(LoginRepositoryIT.class);
    @Autowired
    private LoginRepository loginRepository;

    @BeforeClass
    public static void cassandra() throws InterruptedException, IOException, TTransportException {
        LOGGER.info("Calling init embedded cassandra");
        CassandraTestHelper.initEmbeddedCassandra();
        LOGGER.info("back from calling init embedded cassandra");
    }

    @Test
    public void findByUsername_returns_user(){
        User user = User.builder()
                .username("admin")
                .role("admin")
                .password("admin")
                .build();

        loginRepository.save(user).block();
        StepVerifier.create(loginRepository.findById(user.getUsername()))
                .expectNext(user)
                .expectComplete()
                .verify();
    }

    @After
    public void tearDown(){
        StepVerifier.create(loginRepository.deleteAll())
                .verifyComplete();
    }
}
