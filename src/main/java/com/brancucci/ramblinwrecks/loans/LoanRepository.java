package com.brancucci.ramblinwrecks.loans;

import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends ReactiveCassandraRepository<Loan, String> {
}
