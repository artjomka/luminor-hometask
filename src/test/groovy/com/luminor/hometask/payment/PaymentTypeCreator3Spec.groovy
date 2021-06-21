package com.luminor.hometask.payment

import com.luminor.hometask.payment.dto.CreatePaymentRequest
import spock.lang.Specification
import spock.lang.Subject

class PaymentTypeCreator3Spec extends Specification {

    def paymentRepository = Mock(PaymentRepository)
    @Subject
    def paymentType3Creator = new PaymentType3Creator(paymentRepository)

    def "should check if its applicable for type"() {
        when:
            def type = paymentType3Creator.getPaymentType()
        then:
            type == PaymentType.TYPE3
    }

    def "should throw validation exception on mandatory bic field"() {
        given:
            def createPaymentDTO = new CreatePaymentRequest(100.0, Currency.EUR, "", "", "", "", PaymentType.TYPE3)
        when:
            paymentType3Creator.createPayment(createPaymentDTO)
        then:
            def exception = thrown(PaymentCreationValidationException)
            exception.message == "No mandatory bic set for this processing type TYPE3";
    }
}
