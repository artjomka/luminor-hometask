package com.luminor.hometask.payment;

import com.luminor.hometask.payment.dto.CreatePaymentRequest;
import com.luminor.hometask.payment.dto.CreatePaymentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentType1Creator implements PaymentCreator {

    private final PaymentRepository paymentRepository;

    @Override
    public CreatePaymentResponse createPayment(CreatePaymentRequest request) {
        validate(request);
        var payment = Payment.of(request.amount(), request.currency(), request.debtorIban(), request.creditorIban(), request.paymentType());
        payment.setDetails(request.details());
        var savedPayment = paymentRepository.save(payment);
        return new CreatePaymentResponse(savedPayment.getId());
    }

    private void validate(CreatePaymentRequest createPaymentRequest) {
        if (createPaymentRequest.currency() != Currency.EUR) {
            throw new PaymentCreationValidationException("Wrong currency type for this processing type " + getPaymentType());
        }
        if (createPaymentRequest.details() == null || createPaymentRequest.details().isEmpty()) {
            throw new PaymentCreationValidationException("No mandatory parameter details set for this processing type " + getPaymentType());
        }
    }

    @Override
    public PaymentType getPaymentType() {
        return PaymentType.TYPE1;
    }
}
