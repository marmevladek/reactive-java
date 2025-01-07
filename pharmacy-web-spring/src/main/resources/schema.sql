CREATE TABLE drug (
                      id BIGSERIAL PRIMARY KEY,
                      name VARCHAR(100) NOT NULL,
                      manufacture_date DATE NOT NULL,
                      expiration_date DATE NOT NULL
);

CREATE TABLE pharmacy (
                          id BIGSERIAL PRIMARY KEY,
                          name VARCHAR(100) NOT NULL,
                          address VARCHAR(255) NOT NULL
);

CREATE TABLE pharmacy_drug (
                               pharmacy_id BIGINT REFERENCES pharmacy(id),
                               count BIGINT NOT NULL,
                               drug_id BIGINT REFERENCES drug(id),
                               price DECIMAL(10, 2) NOT NULL,
                               PRIMARY KEY (pharmacy_id, drug_id)
);