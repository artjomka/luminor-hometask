package com.luminor.hometask.payment

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class PaymentRepositorySpec extends AbstractPostgresBaseSpec {

    @Autowired
    PaymentRepository paymentRepository;

    def "should properly save payment"() {
        given:
            def payment = Payment.of(100.0, Currency.EUR, "debtorIban", "creditorIban", PaymentType.TYPE1)
        when:
            def savedEntity = paymentRepository.save(payment)
        then:
            savedEntity.id != null
            savedEntity.status == Payment.PaymentStatus.ACTIVE
    }
}
