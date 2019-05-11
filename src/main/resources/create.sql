CREATE TABLE User(
    id VARCHAR(36) PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    phone VARCHAR(50) NOT NULL,
    gender VARCHAR(2) NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL
);

CREATE TABLE Account(
    id VARCHAR(36) PRIMARY KEY,
    number VARCHAR(10) NOT NULL,
    balance DECIMAL NOT NULL,
    currency VARCHAR(5) NOT NULL,
    user_id VARCHAR(36) NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    optlock BIGINT NOT NULL DEFAULT 0,
    CONSTRAINT FK_user_id FOREIGN KEY (user_id)
        REFERENCES User(id)
);

CREATE TABLE Transaction(
    id VARCHAR(36) PRIMARY KEY,
    from_account_number VARCHAR(10) NOT NULL,
    to_account_number VARCHAR(10) NOT NULL,
    amount DECIMAL NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL
);

CREATE INDEX idx_from_account_number ON Transaction(from_account_number);
CREATE INDEX idx_to_account_number ON Transaction(to_account_number);
CREATE INDEX idx_created_at ON Transaction(created_at);

