package com.brancucci.ramblinwrecks.customer;

import lombok.Builder;
import lombok.Value;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Builder
@Value
@Table
public class Customer {
    @PrimaryKey
    String driversLicenseNumber;
    String firstName;
    String lastName;
    String phone;
    String email;
    String street;
    String city;
    State state;
    String zipCode;

}
