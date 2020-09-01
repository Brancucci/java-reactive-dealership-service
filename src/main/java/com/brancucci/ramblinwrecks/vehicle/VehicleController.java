package com.brancucci.ramblinwrecks.vehicle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class VehicleController {
    private VehicleService vehicleService;
    private static final String ADD_VEHICLE_URI = "/vehicle/add";

    @Autowired
    public VehicleController(VehicleService vehicleService){
        this.vehicleService = vehicleService;
    }

    @PostMapping(path = ADD_VEHICLE_URI)
    public Mono<Vehicle> addVehicle(@RequestBody Vehicle vehicle){
        return vehicleService.addVehicle(vehicle);
    }
}
