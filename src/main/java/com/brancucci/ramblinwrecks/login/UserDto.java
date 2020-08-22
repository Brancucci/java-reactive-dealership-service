package com.brancucci.ramblinwrecks.login;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserDto {
    String username;
    String password;
}
