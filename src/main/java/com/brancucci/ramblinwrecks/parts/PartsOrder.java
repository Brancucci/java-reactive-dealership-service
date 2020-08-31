package com.brancucci.ramblinwrecks.parts;

import lombok.Builder;
import lombok.Value;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import java.math.BigDecimal;

@Table
@Value
@Builder
public class PartsOrder {
    @PrimaryKey
    PartsOrderKey partsOrderKey;
    String description;
    Status status;
    BigDecimal cost;
    String vendor; // create a user defined type for vendor
}
