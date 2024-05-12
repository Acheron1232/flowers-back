-- --liquibase formatted sql

--changeset artem:1
CREATE TABLE users_reset_token
(
    id          bigserial PRIMARY KEY,
    users_id    bigint references users (id) not null ,
    reset_token text
);
