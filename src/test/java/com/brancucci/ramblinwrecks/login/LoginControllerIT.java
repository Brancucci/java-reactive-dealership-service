package com.brancucci.ramblinwrecks.login;

import com.brancucci.ramblinwrecks.RamblinWrecksApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RamblinWrecksApplication.class})
@ActiveProfiles({"test"})
public class LoginControllerIT {
    public LoginController loginController;

    // good login
    @Test
    public void validUserLogin(){

    }

    // bad login
}
