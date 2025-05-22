CREATE TABLE country(
            id BIGSERIAL PRIMARY KEY,
            name VARCHAR(250),
            continent VARCHAR(250)

);

CREATE TABLE host(
            id BIGSERIAL PRIMARY KEY,
            name VARCHAR(250),
            surname VARCHAR(250),
            countryId BIGINT references country(id) on delete cascade
);

CREATE TABLE accommodation(
            id BIGSERIAL PRIMARY KEY,
            name VARCHAR(250),
            category VARCHAR(250),
            hostId BIGINT references host(id) on delete cascade,
            numRooms BIGINT
--     available???
);


CREATE TABLE users (
            username VARCHAR(255) PRIMARY KEY,
            password VARCHAR(255),
            name VARCHAR(255),
            role VARCHAR(255)
);

CREATE TABLE user_accommodation_wishlist (
            user_username VARCHAR(255) REFERENCES users(username),
            accommodation_id BIGINT REFERENCES accommodation(id),
            PRIMARY KEY (user_username, accommodation_id)
);

CREATE TABLE user_rented_accommodations (
            user_username VARCHAR(255) REFERENCES users(username),
            accommodation_id BIGINT REFERENCES accommodation(id),
            PRIMARY KEY (user_username, accommodation_id)
);