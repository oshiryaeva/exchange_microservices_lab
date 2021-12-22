CREATE sequence seq_account_id
increment by 1 
start 1;


CREATE TABLE public.account (
	id int8 NOT NULL,
	balance numeric NOT NULL,
	currency varchar NOT NULL,
	type varchar NOT NULL,
	owner_id int8 NOT NULL,
	CONSTRAINT account_unique_1 UNIQUE (currency, type, owner_id),
	CONSTRAINT account_pk PRIMARY KEY (id)
);


insert into public.account(id, balance, currency, type, owner_id) values(nextval('seq_account_id'), 10000, 'USD', 'COMPANY', 1);
insert into public.account(id, balance, currency, type, owner_id) values(nextval('seq_account_id'), 20000, 'EUR', 'COMPANY', 1);
insert into public.account(id, balance, currency, type, owner_id) values(nextval('seq_account_id'), 50000, 'USD', 'COMPANY', 2);
insert into public.account(id, balance, currency, type, owner_id) values(nextval('seq_account_id'), 60000, 'EUR', 'COMPANY', 2);
insert into public.account(id, balance, currency, type, owner_id) values(nextval('seq_account_id'), 10000, 'EUR', 'TRADER', 1);
insert into public.account(id, balance, currency, type, owner_id) values(nextval('seq_account_id'), 10000, 'USD', 'TRADER', 1);
insert into public.account(id, balance, currency, type, owner_id) values(nextval('seq_account_id'), 12345, 'EUR', 'TRADER', 2);
insert into public.account(id, balance, currency, type, owner_id) values(nextval('seq_account_id'), 43121, 'USD', 'TRADER', 2);
insert into public.account(id, balance, currency, type, owner_id) values(nextval('seq_account_id'), 54545, 'EUR', 'TRADER', 3);
insert into public.account(id, balance, currency, type, owner_id) values(nextval('seq_account_id'), 66654, 'USD', 'TRADER', 3);
insert into public.account(id, balance, currency, type, owner_id) values(nextval('seq_account_id'), 76643, 'EUR', 'TRADER', 4);