package com.luminor.hometask.payment;

import com.luminor.hometask.payment.dto.CancelPaymentRequest;
import com.luminor.hometask.payment.dto.CreatePaymentRequest;
import com.luminor.hometask.payment.dto.CreatePaymentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/payment/")
@RequiredArgsConstructor
public class PaymentController {

    private final List<PaymentCreator> paymentCreators;
    private final PaymentCancellationService paymentCancellationService;

    @PostMapping
    public ResponseEntity<CreatePaymentResponse> create(@RequestBody CreatePaymentRequest createPaymentRequest) {
        var paymentCreator = paymentCreators.stream()
                .filter(pc -> pc.getPaymentType() == createPaymentRequest.paymentType())
                .findFirst()
                .orElseThrow(() -> new UnsupportedOperationException("No type creator found for type" + createPaymentRequest.paymentType()));
        var response = paymentCreator.createPayment(createPaymentRequest);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/cancel")
    public ResponseEntity cancel(@RequestBody CancelPaymentRequest cancelPaymentRequest) {
        paymentCancellationService.cancelPayment(cancelPaymentRequest.id());
        return ResponseEntity.ok().build();
    }
}
