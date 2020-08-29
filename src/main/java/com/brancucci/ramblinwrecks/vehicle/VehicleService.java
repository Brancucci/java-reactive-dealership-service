package com.brancucci.ramblinwrecks.vehicle;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class VehicleService {
    private VehicleRepository vehicleRepository;

    @Autowired
    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public Mono<Vehicle> addVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }
}
