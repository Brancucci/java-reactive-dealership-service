package com.brancucci.ramblinwrecks.exception;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.reactivestreams.Publisher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;
import reactor.util.annotation.Nullable;

import java.util.Optional;

@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = UserAlreadyExists.class)
    public Publisher<ErrorResponse> handleUserAlreadyExistException(Exception e) {
        return Mono.just(buildErrorResponse(e));
    }

    private ErrorResponse buildErrorResponse(Exception e) {
        return buildErrorResponse(e, null);
    }

    private ErrorResponse buildErrorResponse(Exception e, @Nullable String prefix) {
        String errorMsgPrefix = Strings.isBlank(prefix) ? "Exception occurered: {}" : prefix + "Exception occurred: {}";
        log.error(errorMsgPrefix, e);
        log.error((Optional.ofNullable(prefix).orElse("")) + " : Exception occurred : {}" , e.toString());
        return ErrorResponse.builder()
                .errorCode(e.getClass().getSimpleName())
                .errorMEssage(e.getMessage())
                .build();
    }
}
