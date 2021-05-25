CREATE TABLE hotel
(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    star_rating VARCHAR(255) NOT NULL,
--    address VARCHAR(255) NOT NULL,
    total_room INT,
    address_id BIGSERIAL
);

CREATE TABLE room
(
    id BIGSERIAL PRIMARY KEY,
    room_number VARCHAR(255) NOT NULL,
    room_type VARCHAR(255) NOT NULL,
    room_status VARCHAR(255) NOT NULL,
    price BIGSERIAL,
    hotel_id BIGSERIAL
);

CREATE TABLE address
(
    id BIGSERIAL PRIMARY KEY,
    full_address VARCHAR(255) NOT NULL,
    city VARCHAR(255) NOT NULL,
    country VARCHAR(255) NOT NULL
--    ha_id BIGSERIAL
);