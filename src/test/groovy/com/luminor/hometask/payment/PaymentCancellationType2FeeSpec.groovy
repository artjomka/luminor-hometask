package com.luminor.hometask.payment

import spock.lang.Specification
import spock.lang.Subject

import static java.time.Clock.fixed
import static java.time.LocalDateTime.of
import static java.time.LocalDateTime.of
import static java.time.ZoneId.systemDefault
import static java.time.ZoneOffset.UTC

class PaymentCancellationType2FeeSpec extends Specification {
    def currencyConverter = Mock(CurrencyConverter)
    @Subject
    def paymentCancellationFee = new PaymentCancellationType2Fee(currencyConverter)

    def "should calculate cancellation fee"() {
        given:
            paymentCancellationFee.clock = fixed(of(2020, 11, 10, 20, 15, 0).toInstant(UTC), systemDefault())
            def payment = Payment.of(100.0, Currency.EUR, "debtoriban", "creditiban", PaymentType.TYPE1)
            payment.created = of(2020, 11, 10, 14, 0, 0)
        when:
            def calculatedFee = paymentCancellationFee.calculateFee(payment)
        then:
            calculatedFee == 0.81
    }
}
