package com.brancucci.ramblinwrecks.customers.businesscustomer;

import com.brancucci.ramblinwrecks.customers.State;
import lombok.Builder;
import lombok.Value;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Builder
@Value
@Table
public class BusinessCustomer {
    @PrimaryKey
    String taxIdNumber;
    String businessName;
    String primaryContactTitle;
    String primaryContactName;
    String phone;
    String street;
    String city;
    State state;
    String zipCode;
}
