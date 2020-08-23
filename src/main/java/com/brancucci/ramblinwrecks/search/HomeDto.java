package com.brancucci.ramblinwrecks.search;

import lombok.Builder;
import lombok.Value;

import java.util.Set;

@Value
@Builder
public class HomeDto {
    Set<VehicleColors> vehicleColors;
    Set<VehicleManufacturers> vehicleManufacturers;
    Set<VehicleTypes> vehicleTypes;
}
