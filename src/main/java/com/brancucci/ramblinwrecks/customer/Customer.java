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
}
