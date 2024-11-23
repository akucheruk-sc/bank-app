CREATE TABLE IF NOT EXISTS "client" (
    client_id uuid NOT NULL PRIMARY KEY,
    first_name varchar(50) NOT NULL,
    last_name varchar(50) NOT NULL,
    middle_name varchar(50),
    is_active BOOLEAN NOT NULL,
    address_id uuid NOT NULL,
    create_date timestamp  NOT NULL,
    modify_date timestamp
)