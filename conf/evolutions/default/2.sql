# --- !Ups

insert into segmento (nome) values
	('Cama'),
	('Mesa'),
	('Banho');

insert into fornecedor (nome, email, telefone, dt_cadastro, dt_atualizacao, segmento_id, ativo) values
	(
	   'Fornecedor 1', 
	   'forn1@teste.com',
	   '1111111', 
	   now(), 
	   now(),
	   (select id from segmento where nome = 'Cama'),
	   false
	),
	(
	   'Fornecedor 2', 
	   'forn2@teste.com',
	   '2111111', 
	   now(), 
	   now(),
	   (select id from segmento where nome = 'Mesa'),
	   true
	),
	(
	   'Fornecedor 3', 
	   'forn3@teste.com',
	   '3111111', 
	   now(), 
	   now(),
	   (select id from segmento where nome = 'Banho'),
	   true
	);
	
# --- !Downs

delete from fornecedor;
delete from segmento;
