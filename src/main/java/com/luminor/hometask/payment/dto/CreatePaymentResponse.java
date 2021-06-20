package com.luminor.hometask.payment.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public record CreatePaymentResponse(Long id) {
}
