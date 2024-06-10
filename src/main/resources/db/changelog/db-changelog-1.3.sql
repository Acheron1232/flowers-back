-- --liquibase formatted sql

--changeset artem:1
CREATE TABLE property
(
    id         bigserial PRIMARY KEY,
    product_id bigint references product (id),
    ua_key      text,
    en_key      text,
    ua_value    text,
    en_value    text
);
