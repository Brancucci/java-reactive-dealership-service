package com.brancucci.ramblinwrecks.config;

import com.datastax.driver.core.ConsistencyLevel;
import com.datastax.driver.core.HostDistance;
import com.datastax.driver.core.PoolingOptions;
import com.datastax.driver.core.QueryOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.cassandra.config.AbstractReactiveCassandraConfiguration;
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.repository.config.EnableReactiveCassandraRepositories;

@Configuration
@EnableReactiveCassandraRepositories
@Profile("!test")
public class CassandraConfig extends AbstractReactiveCassandraConfiguration {
    @Value("${cassandra.contactpoints}")
    private String contactPoints;
    @Value("${cassandra.port}")
    private int port;
    @Value("${cassandra.keyspace}")
    private String keyspace;
    @Value("${cassandra.basepackages}")
    private String basePackages;
    @Value("${cassandra.username}")
    private String username;
    @Value("${cassandra.password}")
    private String password;

    public CassandraConfig(){}


    @Override
    protected String getKeyspaceName() {
        return keyspace;
    }

    @Override
    protected String getContactPoints(){return contactPoints; }

    @Override
    protected int getPort() {return port; }

    @Override
    public SchemaAction getSchemaAction() { return SchemaAction.CREATE_IF_NOT_EXISTS; }

    @Override
    public String[] getEntityBasePackages() {
        return new String[] {
                basePackages
        };
    }

    public CassandraClusterFactoryBean cluster(){
        PoolingOptions pollingOptions = new PoolingOptions();
        pollingOptions.setMaxRequestsPerConnection(HostDistance.LOCAL, 32768);

        QueryOptions queryOptions = new QueryOptions()
                .setConsistencyLevel(ConsistencyLevel.QUORUM)
                .setSerialConsistencyLevel(ConsistencyLevel.QUORUM);

        final CassandraClusterFactoryBean cluster = new CassandraClusterFactoryBean();
        cluster.setContactPoints(contactPoints);
        cluster.setUsername(username);
        cluster.setPassword(password);
        cluster.setSslEnabled(false);
        cluster.setPoolingOptions(pollingOptions);
        cluster.setQueryOptions(queryOptions);
        cluster.setJmxReportingEnabled(false);
        return cluster;
    }
}
