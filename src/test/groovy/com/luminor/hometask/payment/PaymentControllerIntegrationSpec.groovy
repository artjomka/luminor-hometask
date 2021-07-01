package com.luminor.hometask.payment

import com.luminor.hometask.payment.dto.CancelPaymentRequest
import com.luminor.hometask.payment.dto.CreatePaymentRequest
import com.luminor.hometask.payment.dto.CreatePaymentResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PaymentControllerIntegrationSpec extends AbstractPostgresBaseSpec {

    @Autowired
    TestRestTemplate testRestTemplate

    @LocalServerPort
    int randomServerPort;

    def "should create payment"() {
        given:
            def createPaymentRequest = new CreatePaymentRequest(100.0, Currency.EUR, "debtorIban", "creditorIban", "someinformation here", "", PaymentType.TYPE1)
        when:
            def createdPayment = testRestTemplate.postForEntity("http://localhost:$randomServerPort/payment/", createPaymentRequest, CreatePaymentResponse.class)
            def paymentId = createdPayment.getBody().id()
        then:
            createdPayment.statusCode == HttpStatus.OK
        and:
            def canceledResponseEntity = testRestTemplate.exchange("http://localhost:$randomServerPort/payment/cancel/", HttpMethod.PUT, new HttpEntity<Object>(new CancelPaymentRequest(paymentId)), ResponseEntity.class)
        then:
            canceledResponseEntity != null
    }
}
