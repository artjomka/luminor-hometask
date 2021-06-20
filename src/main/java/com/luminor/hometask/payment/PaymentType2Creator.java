package com.luminor.hometask.payment;

import com.luminor.hometask.payment.dto.CreatePaymentRequest;
import com.luminor.hometask.payment.dto.CreatePaymentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentType2Creator implements PaymentCreator {

    private final PaymentRepository paymentRepository;

    @Override
    public CreatePaymentResponse createPayment(CreatePaymentRequest request) {
        validate(request);
        var payment = Payment.of(request.amount(), request.currency(), request.debtorIban(), request.creditorIban(), request.paymentType());
        payment.setDetails(request.details());
        paymentRepository.save(payment);
        return new CreatePaymentResponse(payment.getId());
    }

    private void validate(CreatePaymentRequest createPaymentRequest) {
        if (createPaymentRequest.currency() != Currency.USD) {
            throw new PaymentCreationValidationException("Wrong currency type for this processing type " + getPaymentType());
        }
    }

    @Override
    public PaymentType getPaymentType() {
        return PaymentType.TYPE2;
    }
}
