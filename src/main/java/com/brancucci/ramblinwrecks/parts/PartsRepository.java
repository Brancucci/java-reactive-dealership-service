package com.brancucci.ramblinwrecks.parts;

import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartsRepository extends ReactiveCassandraRepository<PartsOrder, PartsOrderKey> {
}
