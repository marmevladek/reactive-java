CREATE TABLE drug (
                      id BIGSERIAL PRIMARY KEY,
                      name VARCHAR(100) NOT NULL,
                      manufacture_date DATE NOT NULL,
                      expiration_date DATE NOT NULL,
                      price DECIMAL(10, 2) NOT NULL
);

CREATE TABLE pharmacy (
                          id BIGSERIAL PRIMARY KEY,
                          name VARCHAR(100) NOT NULL,
                          address VARCHAR(255) NOT NULL,
                          latitude DECIMAL(9,6),
                          longitude DECIMAL(9,6)
);

CREATE TABLE pharmacy_drug (
                               pharmacy_id BIGINT REFERENCES pharmacy(id),
                               count BIGINT NOT NULL,
                               drug_id BIGINT REFERENCES drug(id),
                               PRIMARY KEY (pharmacy_id, drug_id),
                               inital_count BIGINT NOT NULL
);

CREATE OR REPLACE FUNCTION calculate_distance(lat1 FLOAT, lon1 FLOAT, lat2 FLOAT, lon2 FLOAT)
RETURNS FLOAT AS $$
DECLARE
r INT = 6371;
    lat1_rad FLOAT;
    lon1_rad FLOAT;
    lat2_rad FLOAT;
    lon2_rad FLOAT;
    delta_lat FLOAT;
    delta_lon FLOAT;
    a FLOAT;
    c FLOAT;
    distance FLOAT;
BEGIN
    lat1_rad = radians(lat1);
    lon1_rad = radians(lon1);
    lat2_rad = radians(lat2);
    lon2_rad = radians(lon2);

    delta_lat = lat2_rad - lat1_rad;
    delta_lon = lon2_rad - lon1_rad;

    a = sin(delta_lat / 2) ^ 2 + cos(lat1_rad) * cos(lat2_rad) * sin(delta_lon / 2) ^ 2;
    c = 2 * atan2(sqrt(a), sqrt(1 - a));

    distance = r * c;

RETURN distance;
END;
$$ LANGUAGE plpgsql;
