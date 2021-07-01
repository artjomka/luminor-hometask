package com.luminor.hometask.payment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class DefaultPaymentCancelationFee implements PaymentCancellationFee {
    private final PaymentFeeCancellationCalculator paymentFeeCancellationCalculator;
    private final CoefficentResolver coefficentResolver;

    @Override
    public BigDecimal calculateFee(Payment payment) {
        return paymentFeeCancellationCalculator.calculateFee(payment, LocalDateTime.now(),
                coefficentResolver.retrieveCoefficentByType(payment.getType()));
    }
}
