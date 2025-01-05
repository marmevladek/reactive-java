CREATE TABLE pharmacological_action (
                                        id BIGSERIAL PRIMARY KEY,
                                        description TEXT NOT NULL,
                                        action_type VARCHAR(50) NOT NULL
);

CREATE TABLE drug (
                      id BIGSERIAL PRIMARY KEY,
                      name VARCHAR(100) NOT NULL,
                      manufacture_date DATE NOT NULL,
                      expiration_date DATE NOT NULL,
                      pharmacological_action_id BIGINT REFERENCES pharmacological_action(id)
);

CREATE TABLE pharmacy (
                          id BIGSERIAL PRIMARY KEY,
                          name VARCHAR(100) NOT NULL,
                          address VARCHAR(255) NOT NULL
);

CREATE TABLE pharmacy_drug (
                               pharmacy_id BIGINT REFERENCES pharmacy(id),
                               drug_id BIGINT REFERENCES drug(id),
                               price DECIMAL(10, 2) NOT NULL,
                               PRIMARY KEY (pharmacy_id, drug_id)
);