package com.brancucci.ramblinwrecks.loans;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class LoanController {
    private LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping(path = "/loan/add")
    public Mono<Loan> addLoan(@RequestBody Loan loan) {
        return loanService.addLoan(loan);
    }
}
