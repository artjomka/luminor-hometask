DROP TABLE IF EXISTS payment;

CREATE TABLE payment
(
    id           SERIAL PRIMARY KEY,
    currency     VARCHAR(3)  not null,
    debtorIban   VARCHAR(30) not null,
    creditorIban VARCHAR(30) not null,
    email        VARCHAR(30),
    amount       NUMERIC,
    created      TIMESTAMP
);