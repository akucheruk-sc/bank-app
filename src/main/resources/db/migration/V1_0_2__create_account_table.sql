CREATE TABLE IF NOT EXISTS "account" (
    account_id uuid NOT NULL PRIMARY KEY,
    "number" varchar(25) NOT NULL,
    currency varchar(20) NOT NULL,
    type varchar(20) NOT NULL,
    is_active BOOLEAN NOT NULL,
    amount numeric(12,2) NOT NULL,
    create_date timestamp  NOT NULL,
    modify_date timestamp
)