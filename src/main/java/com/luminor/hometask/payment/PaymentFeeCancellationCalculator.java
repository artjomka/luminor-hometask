package com.luminor.hometask.payment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Component
@RequiredArgsConstructor
public class PaymentFeeCancellationCalculator {
    private final CurrencyConverter currencyConverter;

    public BigDecimal calculateFee(Payment payment, LocalDateTime cancellationTime, BigDecimal coefficient) {
        var creationTime = payment.getCreated();
        checkCancelationTimeIsValid(creationTime, cancellationTime);
        var hours = creationTime.until(cancellationTime, ChronoUnit.HOURS);
        var calculatedFee = BigDecimal.valueOf(hours).multiply(coefficient);
        if (payment.getCurrency() == Currency.USD) {
            return currencyConverter.convertUSDToEUR(calculatedFee);
        }
        return calculatedFee;
    }

    private void checkCancelationTimeIsValid(LocalDateTime creationTime, LocalDateTime cancellationTime) {
        if (notSameDate(creationTime, cancellationTime)) {
            throw new PaymentCancellationException("Unable to cancel payment");
        }
    }

    private boolean notSameDate(LocalDateTime creationTime, LocalDateTime cancellationTime) {
        return !cancellationTime.toLocalDate().equals(creationTime.toLocalDate());
    }
}
