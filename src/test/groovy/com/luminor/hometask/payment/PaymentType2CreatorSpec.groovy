package com.luminor.hometask.payment

import com.luminor.hometask.payment.dto.CreatePaymentRequest
import spock.lang.Specification
import spock.lang.Subject

import static com.luminor.hometask.payment.PaymentType.*

class PaymentType2CreatorSpec extends Specification {
    def paymentRepository = Mock(PaymentRepository)
    @Subject
    def paymentCreatorType2 = new PaymentType2Creator(paymentRepository)

    def "should check if its applicable for type"() {
        when:
            def type = paymentCreatorType2.getPaymentType()
        then:
            type == TYPE2
    }

    def "should throw validation exception on wrong currency"() {
        given:
            def createPaymentDTO = new CreatePaymentRequest(100.0, Currency.EUR, "", "", "", "", TYPE2)
        when:
            paymentCreatorType2.createPayment(createPaymentDTO)
        then:
            def exception = thrown(PaymentCreationValidationException)
            exception.message == "Wrong currency type for this processing type TYPE2";
    }

    def "should pass with optional empty details field"() {
        given:
            def createPaymentDTO = new CreatePaymentRequest(100.0, Currency.USD, "", "", "", "", TYPE2)
        when:
            paymentCreatorType2.createPayment(createPaymentDTO)
        then:
            noExceptionThrown()
    }
}
