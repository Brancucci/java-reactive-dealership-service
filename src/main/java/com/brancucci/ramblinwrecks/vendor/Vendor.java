package com.brancucci.ramblinwrecks.vendor;

import lombok.Builder;
import lombok.Value;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table
@Value
@Builder
public class Vendor {
    @PrimaryKey
    VendorKey vendorKey;
    String street;
    String city;
    String state;
    String postalCode;
    String phone;
}
