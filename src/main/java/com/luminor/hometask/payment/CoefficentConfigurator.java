package com.luminor.hometask.payment;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
@ConfigurationProperties("configuration")
@Getter
@Setter
public class CoefficentConfigurator {
    BigDecimal coefficentType1;
    BigDecimal coefficentType2;
    BigDecimal coefficentType3;

}
