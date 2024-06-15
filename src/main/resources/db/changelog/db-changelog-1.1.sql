-- --liquibase formatted sql

--changeset artem:1
CREATE TABLE category
(
    id      bigserial PRIMARY KEY,
    ua_name text UNIQUE not null,
    en_name text UNIQUE not null
);

--changeset artem:2
CREATE TABLE product
(
    id             bigserial PRIMARY KEY,
    ua_name        text UNIQUE not null,
    en_name        text UNIQUE not null,
    category_id    int         not null references category (id),
    price_usd      decimal(10,2)     not null,
    price_uah      decimal(10,2)     not null,
    ua_content     text,
    en_content     text,
    ua_description text,
    en_description text
);

--changeset artem:3
CREATE TABLE product_image
(
    id         bigserial PRIMARY KEY,
    image      text UNIQUE not null,
    product_id bigint references product (id)
);
