-- --liquibase formatted sql

--changeset artem:1
CREATE TABLE payment
(
    id      bigserial PRIMARY KEY,
    product_id bigint references product(id) not null unique ,
    discount_price int not null ,
    date timestamp not null
);

