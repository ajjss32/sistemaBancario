CREATE TABLE client(
    id SERIAL PRIMARY KEY,
	name VARCHAR(255) NOT NULL,
	cpf VARCHAR(11) UNIQUE NOT NULL,
	email VARCHAR(255) UNIQUE NOT NULL,
	address TEXT NOT NULL
);

CREATE TABLE account(
    id SERIAL PRIMARY KEY,
    account_number BIGINT UNIQUE NOT NULL,
    balence NUMERIC NOT NULL,
    fk_id_client BIGINT,
    CONSTRAINT account_fk_id_client FOREIGN KEY (fk_id_client)
    REFERENCES client(id) ON DELETE CASCADE
);
CREATE TABLE transactions(
    id SERIAL PRIMARY KEY,
    transactions_date DATE NOT NULL,
    amount NUMERIC NOT NULL,
    fk_id_sender BIGINT,
    fk_id_receiver BIGINT,
    CONSTRAINT transactions_fk_id_sender FOREIGN KEY (fk_id_sender)
    REFERENCES account(id) ON DELETE SET NULL,
    CONSTRAINT transactions_fk_id_receiver FOREIGN KEY (fk_id_receiver)
    REFERENCES account(id) ON DELETE SET NULL
);
