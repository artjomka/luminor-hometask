package com.luminor.hometask.payment;

import com.luminor.hometask.payment.Payment;
import com.luminor.hometask.payment.PaymentType;

import java.math.BigDecimal;

public interface PaymentCancellationFee {

    BigDecimal calculateFee(Payment payment);

    PaymentType getType();
}
