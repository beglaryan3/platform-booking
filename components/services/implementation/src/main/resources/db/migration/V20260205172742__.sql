CREATE TABLE booking.users
(
    user_id           UUID primary key not null,
    first_name        varchar(64)      not null,
    last_name         varchar(64)      not null,
    email             varchar(64)      not null unique,
    password          varchar(64)      not null,
    verification_code varchar(64),
    status            varchar(64),
    role              varchar(64),
    company_id        UUID
);

CREATE TABLE booking.company
(
    company_id    UUID PRIMARY KEY NOT NULL,
    company_name  VARCHAR(64)      NOT NULL,
    company_type  VARCHAR(64)      NOT NULL,
    company_email VARCHAR(64)      NOT NULL UNIQUE,
    working_hours VARCHAR(64),
    phone         VARCHAR(64),
    address       VARCHAR(64)
);


CREATE TABLE booking.tables
(
    table_id        UUID PRIMARY KEY NOT NULL,
    table_number    VARCHAR(64)      NOT NULL,
    seats_count     VARCHAR(64),
    table_type      VARCHAR(64)      NOT NULL,
    is_free         BOOLEAN default  true,
    booking_date    TIMESTAMP default now(),
    departure_date TIMESTAMP default  now(),
    company_id UUID REFERENCES company (company_id)
);