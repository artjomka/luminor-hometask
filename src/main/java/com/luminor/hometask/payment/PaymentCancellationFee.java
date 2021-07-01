package com.luminor.hometask.payment;

import java.math.BigDecimal;

public interface PaymentCancellationFee {

    BigDecimal calculateFee(Payment payment);
}
