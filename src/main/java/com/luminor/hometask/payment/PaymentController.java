package com.luminor.hometask.payment;

import com.luminor.hometask.payment.dto.CreatePaymentRequest;
import com.luminor.hometask.payment.dto.CreatePaymentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("payment")
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

    @PutMapping("/{paymentId}/cancellation")
    public ResponseEntity cancel(@PathVariable Long paymentId){
        paymentCancellationService.cancelPayment(paymentId);
        return ResponseEntity.ok().build();
    }
}
