package com.brancucci.ramblinwrecks.login;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class LoginService {
    LoginRepository loginRepository;

    public LoginService(LoginRepository loginRepository){
        this.loginRepository = loginRepository;
    }

    public Mono<User> login(UserDto userDto) {
        return loginRepository.findById(userDto.getUsername())
                .log();
    }

    public Flux<User> getUsers() {
        return loginRepository.findAll().log();
    }

    public Mono<User> registerUser(User user) {
        return loginRepository.save(user);
    }
}
