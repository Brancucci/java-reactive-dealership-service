package com.brancucci.ramblinwrecks.vehicle;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.Collections;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = VehicleController.class)
@Import(VehicleService.class)
@ActiveProfiles({"test"})
public class VehicleControllerIT {

    private static final String ADD_VEHICLE_URI = "/vehicle/add";

    @Autowired
    private WebTestClient webClient;

    @MockBean
    private VehicleRepository vehicleRepository;

    @Test
    public void addVehicleSuccessfully(){
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

        Mockito.when(vehicleRepository.save(vehicle)).thenReturn(Mono.just(vehicle));

        webClient.post()
                .uri(ADD_VEHICLE_URI)
                .body(BodyInserters.fromObject(vehicle))
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .jsonPath("$.vin").isEqualTo(VIN);
    }
}
