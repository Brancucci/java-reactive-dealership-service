package com.brancucci.ramblinwrecks.login;

import com.brancucci.ramblinwrecks.exception.BadCredentialsException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class LoginService {
    LoginRepository loginRepository;

    public LoginService(LoginRepository loginRepository){
        this.loginRepository = loginRepository;
    }

    public Mono<User> login(UserDto userDto) {
        return loginRepository.findById(userDto.getUsername());
    }
}
