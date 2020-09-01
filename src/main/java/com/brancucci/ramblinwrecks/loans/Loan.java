package com.brancucci.ramblinwrecks.loans;

import com.brancucci.ramblinwrecks.customers.CustomerType;
import lombok.Builder;
import lombok.Value;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import java.time.LocalDate;

@Table
@Value
@Builder
public class Loan {
    @PrimaryKey
    String vin;
    String customerId;
    CustomerType customerType;
    LocalDate loanStartDate;
    int loanTerm;



}
