package com.luminor.hometask.payment;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class PaymentCancelationConfiguration {
    private final BigDecimal type1coefficient = BigDecimal.valueOf(0.05);
    private final BigDecimal type2coefficient = BigDecimal.valueOf(0.05);
}
