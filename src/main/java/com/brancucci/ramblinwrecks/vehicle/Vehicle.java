package com.brancucci.ramblinwrecks.vehicle;

import lombok.Builder;
import lombok.Value;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.math.BigDecimal;
import java.util.Set;

@Table
@Builder
@Value
public class Vehicle {
    @PrimaryKey
    String vin;
    VehicleType type;
    VehicleManufacturer manufacturer;
    String model;
    int year;
    Set<VehicleColor> colors;
    String description;
    BigDecimal mileage;
    BigDecimal salesPrice;
    Condition condition;
}
