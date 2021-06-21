package com.luminor.hometask.payment;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
public class Payment {

    @Id
    private final Long id;
    private LocalDateTime created = LocalDateTime.now();
    private final BigDecimal amount;
    private final Currency currency;
    private final String debtorIban;
    private final String creditorIban;
    private final PaymentType type;

    @Setter(AccessLevel.PACKAGE)
    PaymentStatus status = PaymentStatus.ACTIVE;
    @Setter(AccessLevel.PACKAGE)
    private String details;
    @Setter(AccessLevel.PACKAGE)
    private String bic;
    @Setter(AccessLevel.PACKAGE)
    BigDecimal cancellationFee;

    public Payment(Long id, BigDecimal amount, Currency currency, String debtorIban, String creditorIban, PaymentType type) {
        this.id = id;
        this.creditorIban = creditorIban;
        this.debtorIban = debtorIban;
        this.currency = currency;
        this.amount = amount;
        this.type = type;
    }

    static Payment of(BigDecimal amount, Currency currency, String debtorIban, String creditorIban, PaymentType type) {
        return new Payment(null, amount, currency, debtorIban, creditorIban, type);
    }

    enum PaymentStatus {
        ACTIVE, CANCELED
    }
}
