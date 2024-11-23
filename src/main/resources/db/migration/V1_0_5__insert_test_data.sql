INSERT into address
(address_id, house_number, street, post_code, state, create_date, modify_date)
VALUES('57f284d3-313a-4529-9d8b-e681e44b457c', '354', 'Cloud Dr', 553230, 'MINNESOTA', now(), null);

INSERT into address
(address_id, house_number, street, post_code, state, create_date, modify_date)
VALUES('06e74b8d-7b0a-4564-b180-82cde54df3fb', '8232', 'Highway Rd', 553230, 'OTHER', now(), now());

INSERT into address
(address_id, house_number, street, post_code, state, create_date, modify_date)
VALUES('685faca8-16d9-4b47-a4e3-3dd4f4b68072', '11', 'Independence Sq', 343223, 'WASHINGTON', now(), now());

INSERT into address
(address_id, house_number, street, post_code, state, create_date, modify_date)
VALUES('6a7c95d4-f10e-4830-a8b4-dea77db85763', '99', 'Fanout Queue', 219932, 'NORTH_CAROLINA', now(), now());

INSERT into address
(address_id, house_number, street, post_code, state, create_date, modify_date)
VALUES('6a7c95d4-f10e-4830-a8b4-dea77db85762', '7722', 'Kafka Hw', 446322, 'TEXAS', now(), now());

------------------------------------------------------------------------------------------

INSERT INTO client
(client_id, first_name, last_name, middle_name, is_active, address_id, create_date, modify_date)
VALUES('7284961e-d1a1-4fad-8632-4b5afca00c74', 'Chack', 'Norris', null, true, '57f284d3-313a-4529-9d8b-e681e44b457c', now(), now());

INSERT INTO client
(client_id, first_name, last_name, middle_name, is_active, address_id, create_date, modify_date)
VALUES('57f284d3-313a-4529-9d8b-e681e44b457c', 'Bill', 'Smith', 'Jr', true, '06e74b8d-7b0a-4564-b180-82cde54df3fb', now(), now());

INSERT INTO client
(client_id, first_name, last_name, middle_name, is_active, address_id, create_date, modify_date)
VALUES('06e74b8d-7b0a-4564-b180-82cde54df3fb', 'Maria', 'Tereza', null, true, '685faca8-16d9-4b47-a4e3-3dd4f4b68072', now(), now());

INSERT INTO client
(client_id, first_name, last_name, middle_name, is_active, address_id, create_date, modify_date)
VALUES('685faca8-16d9-4b47-a4e3-3dd4f4b68072', 'Anna', 'Black', null, false, '6a7c95d4-f10e-4830-a8b4-dea77db85763', now(), now());

INSERT INTO client
(client_id, first_name, last_name, middle_name, is_active, address_id, create_date, modify_date)
VALUES('6a7c95d4-f10e-4830-a8b4-dea77db85763', 'Joy', 'Black', null, true, '6a7c95d4-f10e-4830-a8b4-dea77db85762', now(), now());

------------------------------------------------------------------------------------------

INSERT INTO account
(account_id, currency, "type", is_active, amount, create_date, modify_date, "number")
VALUES('7284961e-d1a1-4fad-8632-4b5afca00c74', 'USD', 'CREDIT', true, 223233.99, now(), null, 'FI211234569876543210');

INSERT INTO account
(account_id, currency, "type", is_active, amount, create_date, modify_date, "number")
VALUES('7284961e-d1a1-4fad-8632-4b5afca00c71', 'EUR', 'CHECKING', true, 772.62, now(), null, 'UA211234569876549999');

INSERT INTO account
(account_id, currency, "type", is_active, amount, create_date, modify_date, "number")
VALUES('7284961e-d1a1-4fad-8632-4b5afca00c70', 'EUR', 'SAVING', false, 0.00, now(), null, 'UK954334569876543210');

INSERT INTO account
(account_id, currency, "type", is_active, amount, create_date, modify_date, "number")
VALUES('7284961e-d1a1-4fad-8632-4b5afca00c69', 'CRYPTO', 'CREDIT', true, 772.52, now(), null, 'US211234569876598732');

INSERT INTO account
(account_id, currency, "type", is_active, amount, create_date, modify_date, "number")
VALUES('7284961e-d1a1-4fad-8632-4b5afca00c68', 'CRYPTO', 'SAVING', false, -62.02, now(), null, 'CA777234569876543210');

INSERT INTO account
(account_id, currency, "type", is_active, amount, create_date, modify_date, "number")
VALUES('7284961e-d1a1-4fad-8632-4b5afca00c67', 'USD', 'CREDIT', false, 0.00, now(), null, 'ME821234569876543000');

INSERT INTO account
(account_id, currency, "type", is_active, amount, create_date, modify_date, "number")
VALUES('7284961e-d1a1-4fad-8632-4b5afca00c66', 'USD', 'SAVING', true, 1.20, now(), null, ' AR201234569876548888');

------------------------------------------------------------------------------------------

INSERT INTO client_to_account
(client_id, account_id)
VALUES('7284961e-d1a1-4fad-8632-4b5afca00c74', '7284961e-d1a1-4fad-8632-4b5afca00c74');

INSERT INTO client_to_account
(client_id, account_id)
VALUES('7284961e-d1a1-4fad-8632-4b5afca00c74', '7284961e-d1a1-4fad-8632-4b5afca00c71');

INSERT INTO client_to_account
(client_id, account_id)
VALUES('57f284d3-313a-4529-9d8b-e681e44b457c', '7284961e-d1a1-4fad-8632-4b5afca00c71');

INSERT INTO client_to_account
(client_id, account_id)
VALUES('57f284d3-313a-4529-9d8b-e681e44b457c', '7284961e-d1a1-4fad-8632-4b5afca00c69');

INSERT INTO client_to_account
(client_id, account_id)
VALUES('57f284d3-313a-4529-9d8b-e681e44b457c', '7284961e-d1a1-4fad-8632-4b5afca00c66');

INSERT INTO client_to_account
(client_id, account_id)
VALUES('685faca8-16d9-4b47-a4e3-3dd4f4b68072', '7284961e-d1a1-4fad-8632-4b5afca00c68');

INSERT INTO client_to_account
(client_id, account_id)
VALUES('6a7c95d4-f10e-4830-a8b4-dea77db85763', '7284961e-d1a1-4fad-8632-4b5afca00c67');

INSERT INTO client_to_account
(client_id, account_id)
VALUES('6a7c95d4-f10e-4830-a8b4-dea77db85763', '7284961e-d1a1-4fad-8632-4b5afca00c70');

------------------------------------------------------------------------------------------

INSERT INTO "transaction"
(transaction_id, amount, description, status, account_id, from_account_number, to_account_number, create_date, modify_date, processed_time)
VALUES('1184961e-d1a1-4fad-8632-4b5afca00c79', 28.72, 'Test transaction 1', 'PENDING', '7284961e-d1a1-4fad-8632-4b5afca00c74', 'FI211234569876543210', 'ES211234569876543789', now(), now(), null);

INSERT INTO "transaction"
(transaction_id, amount, description, status, account_id, from_account_number, to_account_number, create_date, modify_date, processed_time)
VALUES('1184961e-d1a1-4fad-8632-4b5afca00c80', -12.00, 'Test transaction 2', 'SUCCESSFUL', '7284961e-d1a1-4fad-8632-4b5afca00c71', 'MD333234569876549111', 'UA211234569876549999', now(), now(), now());

INSERT INTO "transaction"
(transaction_id, amount, description, status, account_id, from_account_number, to_account_number, create_date, modify_date, processed_time)
VALUES('1184961e-d1a1-4fad-8632-4b5afca00c81', 72.99, 'Test transaction 3', 'SUCCESSFUL', '7284961e-d1a1-4fad-8632-4b5afca00c71', 'UA211234569876549999', 'MD333234569876549111', now(), now(), now());

INSERT INTO "transaction"
(transaction_id, amount, description, status, account_id, from_account_number, to_account_number, create_date, modify_date, processed_time)
VALUES('1184961e-d1a1-4fad-8632-4b5afca00c82', 0.22, 'Test transaction 4', 'CANCELED', '7284961e-d1a1-4fad-8632-4b5afca00c66', 'AR201234569876548888', 'IT441234569876548321', now(), now(), null);

INSERT INTO "transaction"
(transaction_id, amount, description, status, account_id, from_account_number, to_account_number, create_date, modify_date, processed_time)
VALUES('1184961e-d1a1-4fad-8632-4b5afca00c83', -9.22, 'Test transaction 5', 'REMOVED', '7284961e-d1a1-4fad-8632-4b5afca00c66', 'US211234569876598732', 'AR201234569876548888', now(), now(), null);

INSERT INTO "transaction"
(transaction_id, amount, description, status, account_id, from_account_number, to_account_number, create_date, modify_date, processed_time)
VALUES('1184961e-d1a1-4fad-8632-4b5afca00c84', -72.22, 'Test transaction 6', 'CREATED', '7284961e-d1a1-4fad-8632-4b5afca00c67', 'ME821234569876543000', 'FI211234569876543210', now(), now(), null);