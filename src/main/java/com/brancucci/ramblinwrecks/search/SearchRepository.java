package com.brancucci.ramblinwrecks.search;

import com.brancucci.ramblinwrecks.Vehicle;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SearchRepository extends ReactiveCassandraRepository<Vehicle, String> {
}
