create sequence seq_trader_id 
increment by 1 
start 1;


CREATE TABLE public.trader (
	id int8 NOT NULL,
	name varchar NOT NULL,
	surname varchar NOT NULL,
	username varchar NOT NULL,
	password varchar NOT NULL,
	CONSTRAINT trader_name_unique UNIQUE (username),
	CONSTRAINT trader_pk PRIMARY KEY (id)
);

insert into public.trader(id, name, surname, username, password) values(nextval('seq_trader_id'), 'Jimi', 'Hendrix', 'jimihendrix', '12345');
insert into public.trader(id, name, surname, username, password) values(nextval('seq_trader_id'), 'Amy', 'Winehouse', 'amywinehouse', '12345');
insert into public.trader(id, name, surname, username, password) values(nextval('seq_trader_id'), 'Kurt', 'Cobain', 'kurtcobain', '12345');
insert into public.trader(id, name, surname, username, password) values(nextval('seq_trader_id'), 'Jim', 'Morisson', 'jimmorisson', '12345');
insert into public.trader(id, name, surname, username, password) values(nextval('seq_trader_id'), 'Janis', 'Joplin', 'janisjoplin', '12345');