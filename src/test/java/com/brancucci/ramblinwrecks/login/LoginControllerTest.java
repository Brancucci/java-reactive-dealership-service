package com.brancucci.ramblinwrecks.login;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class LoginControllerTest {

    private LoginController loginController;

    @BeforeEach
    public void setup(){
        loginController = new LoginController();
    }

//    @Test
//    public void successfulLogin(){
//        String username = "123";
//        User user = User.builder().username(username).build();
//        WebTestClient
//                .bindToController(loginController)
//                .build()
//                .post()
//                .uri("/login")
//                .body(Mono.just(user), User.class)
//                .exchange()
//                .expectHeader().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
//                .expectStatus().is2xxSuccessful()
//                .returnResult(Boolean.class)
//                .getResponseBody()
//                .as(StepVerifier::create)
//                .expectNext(Boolean.TRUE)
//                .expectComplete()
//                .verify();
//    }
}
