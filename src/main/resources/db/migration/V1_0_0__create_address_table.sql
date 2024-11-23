CREATE TABLE IF NOT EXISTS address (
    address_id uuid NOT NULL PRIMARY KEY,
    house_number varchar(20) NOT NULL,
    street varchar(255) NOT NULL,
    post_code int NOT NULL,
    state varchar(50) NOT NULL,
    create_date timestamp  NOT NULL,
    modify_date timestamp
)