package com.luminor.hometask.payment;

import com.luminor.hometask.payment.dto.CreatePaymentRequest;
import com.luminor.hometask.payment.dto.CreatePaymentResponse;

public interface PaymentCreator {

    CreatePaymentResponse createPayment(CreatePaymentRequest createPaymentRequest);

    PaymentType getPaymentType();
}
