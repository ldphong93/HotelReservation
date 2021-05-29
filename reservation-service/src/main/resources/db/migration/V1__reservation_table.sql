CREATE TABLE reservation
(
    id BIGSERIAL PRIMARY KEY,
    hotel_id BIGSERIAL NOT NULL,
    guest_id BIGSERIAL NOT NULL,
    room_id BIGSERIAL NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    rent_fee BIGSERIAL NOT NULL,
    created_time TIMESTAMP WITH TIME ZONE
)