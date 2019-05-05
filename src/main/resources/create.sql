CREATE TABLE User(
    id INT PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL
);

CREATE TABLE Account(
    id INT PRIMARY KEY,
    number VARCHAR(255) NOT NULL,
    balance INT NOT NULL,
    currency VARCHAR(5) NOT NULL,
    user_id INT NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT FK_user_id FOREIGN KEY (user_id)
        REFERENCES User(id)
);

CREATE TABLE Transaction(
    id INT PRIMARY KEY,
    from_account_id INT NOT NULL,
    to_account_id INT NOT NULL,
    amount DECIMAL NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL
);

