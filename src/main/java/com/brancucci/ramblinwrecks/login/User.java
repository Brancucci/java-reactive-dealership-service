package com.brancucci.ramblinwrecks.login;

import lombok.*;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Getter
@ToString
@EqualsAndHashCode
@Builder
@Table
@AllArgsConstructor
public class User {
    @PrimaryKey
    String username;
    String firstname;
    String lastname;
    Role role;
    String password;

}
