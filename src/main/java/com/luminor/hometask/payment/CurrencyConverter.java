package com.luminor.hometask.payment;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CurrencyConverter {
    private final BigDecimal conversionRate = BigDecimal.valueOf(0.84);

    BigDecimal convertUSDToEUR(BigDecimal amount) {
        return amount.multiply(conversionRate);
    }
}
