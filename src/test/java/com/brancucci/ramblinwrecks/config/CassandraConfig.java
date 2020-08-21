package com.brancucci.ramblinwrecks.config;

import com.datastax.driver.core.HostDistance;
import com.datastax.driver.core.PoolingOptions;
import com.datastax.driver.core.QueryLogger;
import lombok.Getter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;
import org.springframework.data.cassandra.config.CassandraSessionFactoryBean;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.core.cql.keyspace.CreateKeyspaceSpecification;
import org.springframework.data.cassandra.core.mapping.BasicCassandraMappingContext;
import org.springframework.data.cassandra.core.mapping.CassandraMappingContext;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;
import java.util.Collections;

@Getter
@Configuration
@EnableCassandraRepositories(basePackages = "com.brancucci.ramblinwrecks.login")
@Profile("test")
public class CassandraConfig extends AbstractCassandraConfiguration {
    private static final Log LOGGER = LogFactory.getLog(CassandraConfig.class);

    @Value("${cassandra.contactpoints}")
    private String contactPoints;

    @Value("${cassandra.port}")
    private int port;

    @Value("${cassandra.keyspace}")
    private String keyspace;

    @Value("${cassandra.basepackages}")
    private String basePackages;


    @Override
    public SchemaAction getSchemaAction() {
        return SchemaAction.CREATE_IF_NOT_EXISTS;
    }


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
    @Bean
    public CassandraClusterFactoryBean cluster(){
        PoolingOptions poolingOptions = new PoolingOptions();
        poolingOptions.setMaxRequestsPerConnection(HostDistance.LOCAL, 32768);
        poolingOptions.setMaxRequestsPerConnection(HostDistance.REMOTE, 2000);

        CreateKeyspaceSpecification keyspaceSpecification = CreateKeyspaceSpecification.createKeyspace(keyspace).ifNotExists();

        final CassandraClusterFactoryBean cluster = new CassandraClusterFactoryBean();
        cluster.setContactPoints(contactPoints);
        cluster.setPort(port);
        cluster.setSslEnabled(false);
        cluster.setJmxReportingEnabled(false);
        cluster.setPoolingOptions(poolingOptions);
        cluster.setKeyspaceCreations(Collections.singletonList(keyspaceSpecification));
        cluster.setLatencyTracker(new QueryLogger.Builder().build());
        LOGGER.info("Cluster created with contact points [127.0.0.1] & port [9142].");
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
