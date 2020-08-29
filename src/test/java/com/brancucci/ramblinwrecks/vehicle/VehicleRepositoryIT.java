package com.brancucci.ramblinwrecks.vehicle;

import com.brancucci.ramblinwrecks.RamblinWrecksApplication;
import com.brancucci.ramblinwrecks.config.CassandraTestHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.transport.TTransportException;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.test.StepVerifier;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collections;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RamblinWrecksApplication.class})
@ActiveProfiles({"test"})
@Slf4j
public class VehicleRepositoryIT {

    @Autowired
    private VehicleRepository vehicleRepository;

    @BeforeClass
    public static void cassandra() throws InterruptedException, IOException, TTransportException {
        CassandraTestHelper.initEmbeddedCassandra();
    }

    @Test
    public void addVehicle_returnsVehicle(){
        final String VIN = "A1234";

        Vehicle vehicle = Vehicle.builder()
                .vin(VIN)
                .colors(Collections.singleton(VehicleColor.ALUMINUM))
                .condition(Condition.EXCELLENT)
                .description("Awesome car")
                .manufacturer(VehicleManufacturer.BMW)
                .mileage(BigDecimal.valueOf(22134))
                .salesPrice(BigDecimal.valueOf(12456))
                .type(VehicleType.SEDAN)
                .model("Z3")
                .year(2019)
                .build();

        vehicleRepository.save(vehicle).block();

        StepVerifier.create(vehicleRepository.findById(VIN))
                .expectNext(vehicle)
                .verifyComplete();
    }

    @After
    public void clearDatabase(){
        log.info("cleaning up DB");
        StepVerifier.create(vehicleRepository.deleteAll()).verifyComplete();
        log.info("cleaned up db");
    }
}
