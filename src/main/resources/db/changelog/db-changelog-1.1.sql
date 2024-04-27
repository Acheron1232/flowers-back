-- --liquibase formatted sql

--changeset artem:1
CREATE TABLE category
(
    id   bigserial PRIMARY KEY,
    name text UNIQUE not null
);
--changeset artem:2
CREATE TABLE product
(
    id          bigserial PRIMARY KEY,
    name        text UNIQUE not null,
    category_id int not null references category (id),
    size        text        not null default 'DEFAULT',
    description text,
);
--changeset artem:3
CREATE TABLE product_image
(
    id         bigserial PRIMARY KEY,
    image      text UNIQUE not null,
    product_id int references product (id),
);


