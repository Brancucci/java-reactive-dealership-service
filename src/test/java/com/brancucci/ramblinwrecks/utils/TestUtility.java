package com.brancucci.ramblinwrecks.utils;

import com.brancucci.ramblinwrecks.customers.State;
import com.brancucci.ramblinwrecks.customers.customer.Customer;

public class TestUtility {

    public static Customer getCustomer(String DL_NUMBER){
        return Customer.builder()
                .driversLicenseNumber(DL_NUMBER)
                .firstName("John")
                .lastName("Smith")
                .street("1234 1st Street")
                .city("Tampa")
                .state(State.FL)
                .email("abc@gmail.com")
                .phone("1112223333")
                .zipCode("12345")
                .build();
    }
}
