package com.luminor.hometask.payment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentCancellationService {
    private final PaymentRepository paymentRepository;
    private final DefaultPaymentCancelationFee defaultPaymentCancelationFee;

    public void cancelPayment(Long paymentId) {
        var payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new PaymentCancellationException("No payment found for id:" + paymentId));
        checkPaymentCanBeCanceled(payment);
        var fee = defaultPaymentCancelationFee.calculateFee(payment);
        payment.setCancellationFee(fee);
        payment.setStatus(Payment.PaymentStatus.CANCELED);
        paymentRepository.save(payment);
    }

    private void checkPaymentCanBeCanceled(Payment payment) {

    }
}
