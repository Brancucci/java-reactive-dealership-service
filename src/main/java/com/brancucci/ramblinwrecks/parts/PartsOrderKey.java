package com.brancucci.ramblinwrecks.parts;

import lombok.Builder;
import lombok.Value;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import java.io.Serializable;

@PrimaryKeyClass
@Value
@Builder
public class PartsOrderKey implements Serializable {
    @PrimaryKeyColumn(type = PrimaryKeyType.PARTITIONED)
    String purchaseOrderNumber; // VIN-DD where VIN is the vin number of the vehicle and DD is the number order
    @PrimaryKeyColumn(type = PrimaryKeyType.CLUSTERED)
    String partNumber; // alphanumeric
}
