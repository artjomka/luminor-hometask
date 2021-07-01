package com.luminor.hometask.payment

import spock.lang.Specification
import spock.lang.Subject

class CoefficentResolverSpec extends Specification {

    def coefficentConfigurator = Mock(CoefficentConfigurator)
    @Subject
    def coefficentResolver = new CoefficentResolver(coefficentConfigurator)

    def "should retreive proper coefficent based on type"() {
        given:
            coefficentConfigurator.getCoefficentType1() >> 1.0
            coefficentConfigurator.getCoefficentType2() >> 2.0
            coefficentConfigurator.getCoefficentType3() >> 2.0
        when:
            def coefficent = coefficentResolver.retrieveCoefficentByType(PaymentType.TYPE2)
        then:
            coefficent == 2.0

    }
}
