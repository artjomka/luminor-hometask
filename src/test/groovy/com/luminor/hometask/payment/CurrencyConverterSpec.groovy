package com.luminor.hometask.payment

import spock.lang.Specification
import spock.lang.Subject

class CurrencyConverterSpec extends Specification {

    @Subject
    def currencyConverter = new CurrencyConverter()

    def "should properly convert usd to eur"() {
        when:
            def conversionResult = currencyConverter.convertUSDToEUR(20.00)
        then:
            conversionResult == 16.80
    }
}
