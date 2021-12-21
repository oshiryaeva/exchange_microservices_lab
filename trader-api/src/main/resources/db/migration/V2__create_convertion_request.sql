create sequence seq_conversion_request_id
increment by 1 
start 1;


CREATE TABLE public.conversion_request (
	id int8 NOT NULL,
	amount  numeric NOT NULL,
	"from" varchar NOT NULL,
	"to" varchar NOT NULL,
	status varchar NOT NULL,
	company_id int8 NOT NULL,
	trader_id int8 NOT NULL,
	CONSTRAINT conversion_request_pk PRIMARY KEY (id)
);
