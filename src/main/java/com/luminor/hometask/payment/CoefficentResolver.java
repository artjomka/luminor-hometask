package com.luminor.hometask.payment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class CoefficentResolver {

    private final CoefficentConfigurator coefficentConfigurator;

    public BigDecimal retrieveCoefficentByType(PaymentType paymentType) {
        return switch (paymentType) {
            case TYPE1 -> coefficentConfigurator.getCoefficentType1();
            case TYPE2 -> coefficentConfigurator.getCoefficentType2();
            case TYPE3 -> coefficentConfigurator.getCoefficentType3();
        };
    }
}
