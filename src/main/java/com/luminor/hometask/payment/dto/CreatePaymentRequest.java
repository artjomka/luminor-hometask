package com.luminor.hometask.payment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.luminor.hometask.payment.Currency;
import com.luminor.hometask.payment.PaymentType;

import java.math.BigDecimal;

@JsonSerialize
public record CreatePaymentRequest(BigDecimal amount,
                                   Currency currency,
                                   @JsonProperty("debtor_iban")
                                   String debtorIban,
                                   @JsonProperty("creditor_iban")
                                   String creditorIban,
                                   String details,
                                   String bic,
                                   PaymentType paymentType) {
}

