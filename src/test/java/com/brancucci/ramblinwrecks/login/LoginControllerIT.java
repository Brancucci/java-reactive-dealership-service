package com.brancucci.ramblinwrecks.login;

import com.brancucci.ramblinwrecks.RamblinWrecksApplication;
import com.brancucci.ramblinwrecks.config.CassandraTestHelper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.thrift.transport.TTransportException;
import org.junit.After;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.io.IOException;

import static org.mockito.Mockito.times;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = LoginController.class)
@Import(LoginService.class)
@ActiveProfiles({"test"})
public class LoginControllerIT {
    private static final Log LOGGER = LogFactory.getLog(LoginControllerIT.class);

    @MockBean
    LoginRepository loginRepository;

    @Autowired
    private WebTestClient webClient;

    // good login
    @Test
    public void validUserLogin(){
        final String USERNAME = "admin";
        UserDto userDto = UserDto.builder()
                .username(USERNAME)
                .password(USERNAME)
                .build();

        User user = User.builder()
                .username(USERNAME)
                .role(USERNAME)
                .password(USERNAME)
                .build();

        Mockito.when(loginRepository.findById(USERNAME)).thenReturn(Mono.just(user));

        User userResult = webClient.post()
                .uri("/login")
                .body(BodyInserters.fromObject(userDto))
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(User.class)
                .returnResult()
                .getResponseBody();

        Mockito.verify(loginRepository, times(1)).findById(USERNAME);

        StepVerifier.create(Mono.just(userResult))
                .expectNext(user)
                .verifyComplete();

    }

    // bad login

}
