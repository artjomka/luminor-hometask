package com.luminor.hometask.payment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PaymentCancellationType1Fee implements PaymentCancellationFee {
    private final PaymentFeeCancellationCalculator paymentFeeCancellationCalculator;
    BigDecimal coefficient = BigDecimal.valueOf(0.05);

    @Override
    public BigDecimal calculateFee(Payment payment) {
        return paymentFeeCancellationCalculator.calculateFee(payment, LocalDateTime.now(), coefficient);
    }

    @Override
    public PaymentType getType() {
        return PaymentType.TYPE1;
    }
}
