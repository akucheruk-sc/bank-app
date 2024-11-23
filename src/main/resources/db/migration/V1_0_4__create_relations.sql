ALTER TABLE "transaction"
      ADD CONSTRAINT fk_tr_acc_id
      FOREIGN KEY (account_id) REFERENCES account(account_id);

CREATE TABLE IF NOT EXISTS "client_to_account" (
    client_id uuid,
    account_id uuid,
    CONSTRAINT client_account_pk PRIMARY KEY (client_id, account_id),
    CONSTRAINT fk_client FOREIGN KEY (client_id) REFERENCES client (client_id),
    CONSTRAINT fk_account FOREIGN KEY (account_id) REFERENCES account (account_id)
);

ALTER TABLE "client"
    ADD CONSTRAINT fk_client_address_id
    FOREIGN KEY (address_id) REFERENCES address(address_id);