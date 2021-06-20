DROP TABLE IF EXISTS payment;

CREATE TABLE payment
(
    id               SERIAL PRIMARY KEY,
    currency         VARCHAR(3)  not null,
    status           VARCHAR(10) not null,
    type             VARCHAR(10) not null,
    debtor_iban      VARCHAR(50) not null,
    creditor_iban    VARCHAR(50) not null,
    details          VARCHAR(255),
    bic              VARCHAR(30),
    amount           NUMERIC     not null,
    cancellation_fee NUMERIC,
    created          TIMESTAMP
);