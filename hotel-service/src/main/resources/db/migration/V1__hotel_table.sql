CREATE TABLE hotel
(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    star_rating VARCHAR(255) NOT NULL,
    address_id BIGSERIAL
);

CREATE TABLE address
(
    id BIGSERIAL PRIMARY KEY,
    full_address VARCHAR(255) NOT NULL,
    city VARCHAR(255) NOT NULL,
    country VARCHAR(255) NOT NULL
);

CREATE TABLE room
(
    id BIGSERIAL PRIMARY KEY,
    room_number VARCHAR(255) NOT NULL,
    hotel_id BIGSERIAL,
    room_type_id BIGSERIAL
);

CREATE TABLE room_type
(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    capacity INT NOT NULL,
    rent_fee BIGSERIAL
--    hotel_id BIGSERIAL
);

