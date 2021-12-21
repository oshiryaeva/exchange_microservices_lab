create sequence seq_company_id 
increment by 1 
start 1;


CREATE TABLE public.company (
	id int8 NOT NULL,
	name varchar NOT NULL
);


insert into public.company(id, name) values(nextval('seq_company_id'), 'Exchange Service 1');
insert into public.company(id, name) values(nextval('seq_company_id'), 'Exchange Service 2');