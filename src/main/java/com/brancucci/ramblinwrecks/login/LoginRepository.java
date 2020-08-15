package com.brancucci.ramblinwrecks.login;

import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends ReactiveCassandraRepository<User, UserKey> {
}
