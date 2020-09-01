package com.brancucci.ramblinwrecks.loans;

import com.brancucci.ramblinwrecks.customers.CustomerType;
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

import java.time.LocalDate;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = LoanController.class)
@Import(LoanService.class)
@ActiveProfiles({"test"})
public class LoanControllerIT {
    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private LoanRepository loanRepository;

    private static final String LOAN_ADD_URI = "/loan/add";

    @Test
    public void addLoan_success(){
        LocalDate startDate = LocalDate.now();
        Loan loan = Loan.builder()
                .vin("A1234")
                .loanStartDate(startDate)
                .loanTerm(60)
                .customerId("John Smith")
                .customerType(CustomerType.INDIVIDUAL)
                .build();

        Mockito.when(loanRepository.save(loan)).thenReturn(Mono.just(loan));

        webTestClient.post()
                .uri(LOAN_ADD_URI)
                .body(BodyInserters.fromObject(loan))
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.vin").isEqualTo(loan.getVin());
    }
}
