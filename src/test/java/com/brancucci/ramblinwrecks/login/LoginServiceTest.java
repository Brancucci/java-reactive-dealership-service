package com.brancucci.ramblinwrecks.login;


import com.brancucci.ramblinwrecks.exception.BadCredentialsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import reactor.test.StepVerifier;

public class LoginServiceTest {
    LoginService loginService;
    @Mock
    LoginRepository loginRepository;
}
