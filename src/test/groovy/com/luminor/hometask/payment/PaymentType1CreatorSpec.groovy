package com.luminor.hometask.payment

import com.luminor.hometask.payment.dto.CreatePaymentRequest
import spock.lang.Specification
import spock.lang.Subject

import static com.luminor.hometask.payment.PaymentType.TYPE1

class PaymentType1CreatorSpec extends Specification {

    def paymentRepository = Mock(PaymentRepository)
    @Subject
    def paymentCreatorType1 = new PaymentType1Creator(paymentRepository)

    def "should check if its applicable for type"() {
        when:
            def type = paymentCreatorType1.getPaymentType()
        then:
            type == TYPE1
    }

    def "should throw validation exception on wrong currency"() {
        given:
            def createPaymentDTO = new CreatePaymentRequest(100.0, Currency.USD, "", "", "", "", TYPE1)
        when:
            paymentCreatorType1.createPayment(createPaymentDTO)
        then:
            def exception = thrown(PaymentCreationValidationException)
            exception.message == "Wrong currency type for this processing type TYPE1";
    }

    def "should throw validation exception on mandatory details field"() {
        given:
            def createPaymentDTO = new CreatePaymentRequest(100.0, Currency.EUR, "", "", "", "", TYPE1)
        when:
            paymentCreatorType1.createPayment(createPaymentDTO)
        then:
            def exception = thrown(PaymentCreationValidationException)
            exception.message == "No mandatory parameter details set for this processing type TYPE1";
    }

    def "should pass validation"() {
        given:
            def createPaymentDTO = new CreatePaymentRequest(100.0, Currency.EUR, "", "", "details", "", TYPE1)
        when:
            paymentCreatorType1.createPayment(createPaymentDTO)
        then:
            noExceptionThrown()
    }
}
