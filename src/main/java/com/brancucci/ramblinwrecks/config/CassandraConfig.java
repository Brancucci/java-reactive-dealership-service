package com.brancucci.ramblinwrecks.config;

import com.datastax.driver.core.ConsistencyLevel;
import com.datastax.driver.core.HostDistance;
import com.datastax.driver.core.PoolingOptions;
import com.datastax.driver.core.QueryOptions;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.cassandra.config.AbstractReactiveCassandraConfiguration;
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.core.mapping.CassandraMappingContext;
import org.springframework.data.cassandra.repository.config.EnableReactiveCassandraRepositories;

@Configuration
@EnableReactiveCassandraRepositories(basePackages = "com.brancucci.ramblinwrecks.login")
@Profile("!test")
@Slf4j
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


    @Override
    public String[] getEntityBasePackages(){
        return new String[]{
                "com.brancucci.ramblinwrecks.login"
        };
    }


    @Override
    protected String getKeyspaceName() {
        return keyspace;
    }

    @Override
    protected String getContactPoints(){return contactPoints; }

    @Override
    protected int getPort() {return port; }

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

    @Override
    @Bean
    public CassandraMappingContext cassandraMapping() throws ClassNotFoundException {
        CassandraMappingContext mappingContext = new CassandraMappingContext();
        mappingContext.setInitialEntitySet(getInitialEntitySet());
        return mappingContext;
    }
}
