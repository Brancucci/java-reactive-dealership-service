package com.brancucci.ramblinwrecks.vendor;

import lombok.Builder;
import lombok.Value;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

@PrimaryKeyClass
@Value
@Builder
public class VendorKey {
    @PrimaryKeyColumn(type = PrimaryKeyType.PARTITIONED)
    String vendorName; // VIN-DD where VIN is the vin number of the vehicle and DD is the number order
    @PrimaryKeyColumn(type = PrimaryKeyType.CLUSTERED)
    String partNumber; // alphanumeric
}
