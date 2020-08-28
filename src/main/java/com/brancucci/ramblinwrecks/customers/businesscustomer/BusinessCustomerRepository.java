package com.brancucci.ramblinwrecks.customers.businesscustomer;

import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessCustomerRepository extends ReactiveCassandraRepository<BusinessCustomer, String> {
}
