package com.luminor.hometask.payment;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PaymentCancellationException extends RuntimeException {
    public PaymentCancellationException(String message) {
        super(message);
    }
}
