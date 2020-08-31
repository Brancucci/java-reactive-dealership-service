package com.brancucci.ramblinwrecks.vendor;

import lombok.Builder;
import lombok.Value;
import org.springframework.data.cassandra.core.mapping.Table;

@Table
@Value
@Builder
public class Vendor {
    String vendorName;
    String street;
    String city;
    String state;
    String postalCode;
    String phone;
}
