package com.brancucci.ramblinwrecks.parts;

import com.brancucci.ramblinwrecks.customers.customer.CustomerRepository;
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

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = PartsController.class)
@Import(PartsService.class)
@ActiveProfiles({"test"})
public class PartsControllerIT {
    @MockBean
    private PartsRepository partsRepository ;
    private static final String PARTS_ADD_URI = "/parts/add";

    @Autowired
    private WebTestClient webClient;

    @Test
    public void addPart_success(){
        final String PO_NUMBER = "1234";

        PartsOrderKey partsOrderKey = PartsOrderKey.builder()
                .purchaseOrderNumber("1234")
                .partNumber("A1111")
                .build();

        PartsOrder partsOrder = PartsOrder.builder()
                .partsOrderKey(partsOrderKey)
                .cost(BigDecimal.valueOf(150.00))
                .description("Fan Belt")
                .status(Status.ORDERED)
                .vendor("Vendor 1")
                .build();

        Mockito.when(partsRepository.save(partsOrder)).thenReturn(Mono.just(partsOrder));


        webClient.post()
                .uri(PARTS_ADD_URI)
                .body(BodyInserters.fromObject(partsOrder))
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.status").isEqualTo(partsOrder.getStatus().toString());
    }
}
