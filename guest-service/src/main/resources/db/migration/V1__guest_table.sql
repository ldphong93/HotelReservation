CREATE TABLE guest
(
    id BIGSERIAL PRIMARY KEY,
    user_name VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    phone_number VARCHAR(255),
    created_date_time TIMESTAMP WITH TIME ZONE
)