package com.arexe.bgames.payu;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class PaymentNotAcceptableException extends RuntimeException {

    public PaymentNotAcceptableException(String message) {
        super(message);
    }

    public PaymentNotAcceptableException(String message, Throwable cause) {
        super(message, cause);
    }

}
