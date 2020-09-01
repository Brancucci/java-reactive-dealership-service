package com.brancucci.ramblinwrecks.loans;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class LoanService {
    private LoanRepository loanRepository;

    public LoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    public Mono<Loan> addLoan(Loan loan) {
        return loanRepository.save(loan);
    }
}
