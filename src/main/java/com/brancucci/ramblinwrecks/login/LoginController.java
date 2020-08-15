package com.brancucci.ramblinwrecks.login;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/login")
public class LoginController {

    public LoginController(){}

    @PostMapping
    public Mono<Boolean> login(){
        return Mono.empty();
    }

}
