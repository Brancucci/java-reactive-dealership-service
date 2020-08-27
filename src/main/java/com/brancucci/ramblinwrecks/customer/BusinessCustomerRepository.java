package com.brancucci.ramblinwrecks.customer;

import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessCustomerRepository extends ReactiveCassandraRepository<BusinessCustomer, String> {
}
