package com.luminor.hometask.payment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class PaymentCancellationType1Fee implements PaymentCancellationFee {
    private final CurrencyConverter currencyConverter;
    BigDecimal coefficient = BigDecimal.valueOf(0.05);
    Clock clock = Clock.systemUTC();

    @Override
    public BigDecimal calculateFee(Payment payment) {
        var cancellationTime = LocalDateTime.now(clock);
        var creationTime = payment.getCreated();
        var hours = creationTime.until(cancellationTime, ChronoUnit.HOURS);
        var calculatedFee = BigDecimal.valueOf(hours).multiply(coefficient);
        if (payment.getCurrency() == Currency.USD) {
            return currencyConverter.convertUSDToEUR(calculatedFee);
        }
        return calculatedFee;
    }

    @Override
    public PaymentType getType() {
        return PaymentType.TYPE1;
    }
}
