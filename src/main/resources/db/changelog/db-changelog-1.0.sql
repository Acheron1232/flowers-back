-- --liquibase formatted sql

--changeset artem:1
CREATE TABLE users
(
    id           bigserial PRIMARY KEY,
    first_name   text        not null,
    last_name    text        not null,
    email        text UNIQUE not null,
    password     text        not null,
    phone_number text unique,
    role         text        not null
);
