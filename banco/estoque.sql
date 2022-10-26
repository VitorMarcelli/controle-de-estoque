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

-- READ 1 (selecionar todos os usuários)

-- READ 2 (selecionar um usuário específico por id)

-- UPDATE (alterar todos os dados de um usuário específico)

-- DELETE (excluir um usuário específico)

-- Gerar a documentação - Modelo ER (engenharia reversa)
 