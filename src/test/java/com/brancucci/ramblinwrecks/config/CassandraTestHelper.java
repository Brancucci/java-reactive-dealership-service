package com.brancucci.ramblinwrecks.config;


import com.datastax.driver.core.Session;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.thrift.transport.TTransportException;
import org.cassandraunit.utils.EmbeddedCassandraServerHelper;
import org.springframework.context.annotation.Bean;

import java.io.IOException;

public class CassandraTestHelper {
    private static final Log LOGGER = LogFactory.getLog(CassandraTestHelper.class);

    public static void initEmbeddedCassandra() throws TTransportException, IOException, InterruptedException {
        LOGGER.info("Initializing embedded Cassandra");
        EmbeddedCassandraServerHelper.startEmbeddedCassandra();
        LOGGER.info("Connected to embedded db");

    }

}
