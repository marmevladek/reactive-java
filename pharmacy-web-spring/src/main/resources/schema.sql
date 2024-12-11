-- Таблица pharmacological_action
CREATE TABLE IF NOT EXISTS pharmacological_action (
                                                      id SERIAL PRIMARY KEY,
                                                      description TEXT NOT NULL,
                                                      action_type TEXT NOT NULL
);

-- Таблица drug
CREATE TABLE IF NOT EXISTS drug (
                                    id SERIAL PRIMARY KEY,
                                    name TEXT NOT NULL,
                                    manufacture_date DATE NOT NULL,
                                    expiration_date DATE NOT NULL,
                                    pharmacological_action_id INT NOT NULL REFERENCES pharmacological_action(id) ON DELETE CASCADE
);

-- Таблица pharmacy
CREATE TABLE IF NOT EXISTS pharmacy (
                                        id SERIAL PRIMARY KEY,
                                        name TEXT NOT NULL,
                                        address TEXT NOT NULL
);

-- Таблица pharmacy_drug
CREATE TABLE IF NOT EXISTS pharmacy_drug (
                                             pharmacy_id INT NOT NULL REFERENCES pharmacy(id) ON DELETE CASCADE,
                                             drug_id INT NOT NULL REFERENCES drug(id) ON DELETE CASCADE,
                                             price NUMERIC(10, 2) NOT NULL,
                                             PRIMARY KEY (pharmacy_id, drug_id)
);
--
-- CREATE TABLE IF NOT EXISTS pharmacy_drug (
--                                pharmacy_id BIGINT NOT NULL,
--                                drug_id BIGINT NOT NULL,
--                                price DECIMAL(10, 2) NOT NULL,
--                                PRIMARY KEY (pharmacy_id, drug_id),
--                                FOREIGN KEY (pharmacy_id) REFERENCES pharmacy(id),
--                                FOREIGN KEY (drug_id) REFERENCES drug(id)
-- );