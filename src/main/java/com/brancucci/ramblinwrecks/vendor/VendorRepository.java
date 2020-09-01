package com.brancucci.ramblinwrecks.vendor;

import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorRepository extends ReactiveCassandraRepository<Vendor, VendorKey> {
}
