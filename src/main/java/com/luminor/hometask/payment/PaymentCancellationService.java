package com.luminor.hometask.payment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentCancellationService {
    private final PaymentRepository paymentRepository;
    private final List<PaymentCancellationFee> paymentCancellationFees;

    public void cancelPayment(Long paymentId) {
        var payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new PaymentCancellationException("No payment found for id:" + paymentId));
        var paymentCancellationFee = paymentCancellationFees.stream()
                .filter(it -> it.getType() == payment.getType())
                .findFirst()
                .orElseThrow(() -> new PaymentCancellationException("No payment type found for payment: " + paymentId));
        paymentCancellationFee.calculateFee(payment);

    }
}
