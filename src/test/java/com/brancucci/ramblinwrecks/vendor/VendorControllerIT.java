package com.brancucci.ramblinwrecks.vendor;

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

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = VendorController.class)
@Import(VendorService.class)
@ActiveProfiles({"test"})
public class VendorControllerIT {
    private static final String VENDOR_LOOKUP_URI = "/vendor/lookup";

    @Autowired
    private WebTestClient webClient;

    @MockBean
    private VendorRepository vendorRepository;


    @Test
    public void vendorLookup_returnsVendor() {
        VendorKey vendorKey = VendorKey.builder()
                .vendorName("Vendor")
                .partNumber("A1234")
                .build();

        Vendor vendor = Vendor.builder()
                .vendorKey(vendorKey)
                .street("123 1st St.")
                .city("Tampa")
                .state("FL")
                .postalCode("33647")
                .phone("1234567890")
                .build();

        Mockito.when(vendorRepository.findById(vendorKey)).thenReturn(Mono.just(vendor));


        webClient.post()
                .uri(VENDOR_LOOKUP_URI)
                .body(BodyInserters.fromObject(vendorKey))
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.city").isEqualTo("Tampa");
    }
}
