package com.luminor.hometask.payment;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Clock;

@Service
public class PaymentCancellationType3Fee implements PaymentCancellationFee {
    private final BigDecimal coefficient = BigDecimal.valueOf(0.15);
    private Clock clock = Clock.systemUTC();

    @Override
    public BigDecimal calculateFee(Payment payment) {
        return null;
    }

    @Override
    public PaymentType getType() {
        return PaymentType.TYPE3;
    }
}
