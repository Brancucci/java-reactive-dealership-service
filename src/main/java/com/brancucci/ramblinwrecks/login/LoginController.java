package com.brancucci.ramblinwrecks.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class LoginController {

    private LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService){
        this.loginService = loginService;
    }

    @PostMapping(value = "/login")
    public Mono<User> login(@RequestBody UserDto userDto){
        return loginService.login(userDto)
            .switchIfEmpty(Mono.empty());
    }

    @GetMapping(value = "/users")
    public Flux<User> getUsers() {
        return loginService.getUsers().log();
    }

    @PostMapping(value = "/register")
    public Mono<User> registerUser(@RequestBody User user){
        return loginService.registerUser(user);
    }

}
