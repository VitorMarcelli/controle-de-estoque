/**
 * Projeto de um sistema para gestão de estoque
 * @author Professor José de Assis
 * @version 1.0
 */
 
 create database dbestoque;
 use dbestoque;
 
 create table usuarios (
	id int primary key auto_increment,
    usuario varchar(50) not null,
    login varchar(20) not null,
    senha varchar(250) not null
);

describe usuarios;

/************** CRUD *****************/

-- CREATE (inserir 5 usuários)
insert into contatos (contato, telefone, email) 
	values ('bill Gates','99999-1234','bill@outlook.com');
    
insert into contatos (contato, telefone, email) 
values ('Batman','99999-1323','batmovel@outlook.com');

insert into contatos (contato, telefone, email) 
values ('Bruno','99999-1322','bruno@outlook.com');

insert into contatos (contato, telefone, email) 
values ('Vitor ','99999-1585','vitor@outlook.com');

insert into contatos (contato, telefone, email) 
values ('Tatiane Regina ','99999-1585','tatiane@outlook.com');

insert into contatos (contato, telefone) 
values ('Bruce Wayne ','99999-6868');
-- READ 1 (selecionar todos os usuários)
select * from usuarios;
-- READ 2 (selecionar um usuário específico por id)
select * from usuarios
where usuario = 'Vitor Marcelli';
-- UPDATE (alterar todos os dados de um usuário específico)
update contatos set usuario = ?, login = pedro, senha = 123@senac where id = 7;
-- DELETE (excluir um usuário específico)
delete from contatos where id = 7;
-- Gerar a documentação - Modelo ER (engenharia reversa)
 