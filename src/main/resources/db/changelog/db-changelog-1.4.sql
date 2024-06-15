-- --liquibase formatted sql

--changeset artem:1
CREATE TABLE comment
(
    id         bigserial PRIMARY KEY,
    product_id bigint references product (id),
    author     bigint       not null references users(id),
    date       timestamp not null,
    text       text      not null
);

--changeset artem:2
CREATE TABLE shop_comment
(
    id         bigserial PRIMARY KEY,
    author     bigint       not null references users(id),
    date       timestamp not null,
    text       text      not null
);
