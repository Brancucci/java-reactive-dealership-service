package com.brancucci.ramblinwrecks.login;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserDto {
    private String username;
    private String password;
}
