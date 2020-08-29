package com.brancucci.ramblinwrecks.vehicle;

import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends ReactiveCassandraRepository<Vehicle, String> {
}
