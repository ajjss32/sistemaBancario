CREATE TABLE client(
    id SERIAL PRIMARY KEY,
	name VARCHAR(255) NOT NULL,
	cpf VARCHAR(11) NOT NULL,
	email VARCHAR(255) NOT NULL,
	address TEXT NOT NULL
);

CREATE TABLE account(
    id SERIAL PRIMARY KEY,
    account_number BIGINT NOT NULL,
    balence NUMERIC NOT NULL,
    fk_id_client BIGINT NOT NULL,
    CONSTRAINT account_fk_id_client FOREIGN KEY (fk_id_client)
    REFERENCES client(id) ON DELETE CASCADE
);
CREATE TABLE transactions(
    id SERIAL PRIMARY KEY,
    transactions_date DATE NOT NULL,
    amount NUMERIC NOT NULL,
    fk_id_sender BIGINT NOT NULL,
    fk_id_receiver BIGINT NOT NULL,
    CONSTRAINT transactions_fk_id_sender FOREIGN KEY (fk_id_sender)
    REFERENCES client(id) ON DELETE SET NULL,
    CONSTRAINT transactions_fk_id_receiver FOREIGN KEY (fk_id_receiver)
    REFERENCES client(id) ON DELETE SET NULL
);
