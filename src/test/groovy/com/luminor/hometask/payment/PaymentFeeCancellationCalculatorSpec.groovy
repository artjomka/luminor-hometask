package com.luminor.hometask.payment

import spock.lang.Specification
import spock.lang.Subject

import java.time.LocalDateTime

import static java.time.LocalDateTime.of

class PaymentFeeCancellationCalculatorSpec extends Specification {
    def currencyConverter = Mock(CurrencyConverter)

    @Subject
    def paymentFeeCancellationCalculator = new PaymentFeeCancellationCalculator(currencyConverter)

    def "should calculate cancellation fee for various coefficients"() {
        given:
            def payment = Payment.of(100.0, Currency.EUR, "debtoriban", "creditiban", PaymentType.TYPE1)
            payment.created = of(2020, 11, 10, 14, 0, 0)
        when:
            def calculatedFee = paymentFeeCancellationCalculator.calculateFee(payment, cancellationTime, coefficient)
        then:
            result == calculatedFee
        where:
            cancellationTime            | coefficient || result
            of(2020, 11, 10, 21, 15, 0) | 0.05        || 0.35
            of(2020, 11, 10, 23, 16, 0) | 0.1         || 0.9
    }

    def "should not able to cancel payment because of date difference" () {
        given:
            def payment = Payment.of(100.0, Currency.EUR, "debtoriban", "creditiban", PaymentType.TYPE1)
            payment.created = of(2020, 11, 10, 14, 0, 0)
            def cancellationTime = of(2020, 11, 11, 0, 0, 0)
        when:
            paymentFeeCancellationCalculator.calculateFee(payment, cancellationTime, 0.01)
        then:
            def exception = thrown(PaymentCancellationException)
            exception.message == "Unable to cancel payment"
    }
}
