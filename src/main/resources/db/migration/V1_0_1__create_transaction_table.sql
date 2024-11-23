CREATE TABLE IF NOT EXISTS "transaction" (
    transaction_id uuid NOT NULL PRIMARY KEY,
    amount numeric(12,2) NOT NULL,
    description varchar(255) NOT NULL,
    processed_time timestamp,
    status varchar(20) NOT NULL,
    account_id uuid NOT NULL,
    from_account_number varchar(25),
    to_account_number varchar(25),
    create_date timestamp NOT NULL,
    modify_date timestamp
)