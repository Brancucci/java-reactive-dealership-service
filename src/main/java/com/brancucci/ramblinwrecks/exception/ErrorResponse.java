package com.brancucci.ramblinwrecks.exception;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ErrorResponse {
    String errorCode;
    String errorMEssage;
}
