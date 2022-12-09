/**
* Controle de estoque
* @author Professor José de Assis
* @version 1.3
*/

create database dbestoque;
use dbestoque;

create table usuarios(
	id int primary key auto_increment,
    usuario varchar(50) not null,
    login varchar(20) not null unique,
    senha varchar(250) not null,
    perfil varchar(50) not null
);

-- CRUD Create
insert into usuarios (usuario,login,senha,perfil)
values('Administrador','admin',md5('admin'),'admin');

describe usuarios;

-- CRUD Read
select * from usuarios;
select * from usuarios where login = 'admin';

-- CRUD Update
update usuarios set usuario = 'José de Assis Filho', login = 'ze',
senha = md5('123@senac'), perfil = 'user' where id = 2;

-- CRUD Delete
delete from usuarios where id = 5;

create table fornecedores (
	idFor int primary key auto_increment,
	razaoSocial varchar(50) not null,
	fantasia varchar(50) not null,
	cnpj varchar(20) unique,
	ie varchar(20) unique,
	cep varchar(10) not null,
	endereco varchar(50) not null,
	numero varchar(6) not null,
	complemento varchar(20),
	bairro varchar(50) not null,
	cidade varchar(50) not null,
	uf char(2) not null,
	nomeContato varchar(30) not null,
	fone varchar(15) not null,
	whatsapp varchar(15),
	email varchar(50) not null,
	site varchar(50),
	obs varchar(250)
);

describe fornecedores;

insert into fornecedores (razaoSocial, fantasia, cnpj, ie, cep, endereco, numero, complemento, bairro, cidade, uf, nomeContato, fone, whatsapp, email, site, obs) values ('Kalunga', 'Kalunga', '43.283.811/0059-76', '206292929110', '70711-000', 'Rua dos Kalungas', '1000', '', 'Distrito Asa Norte', 'Brasilia', 'DF', 'Luiz Carlos', '1234-5678', '1234-0000', 'luiz.carlos@kalunga.com.br', 'www.kalunga.com.br', 'Pode pá');
insert into fornecedores (razaoSocial, fantasia, cnpj, ie, cep, endereco, numero, complemento, bairro, cidade, uf, nomeContato, fone, whatsapp, email, site, obs) values ('Kalango', 'Kalango', '44.283.811/0059-76', '207292929110', '70712-000', 'Rua dos Calangos', '2000', '', 'Calango do Norte', 'Amapá', 'AP', 'João Pedro', '1111-0000', '2222-0000', 'joao.pedro@kalango.com.br', 'www.kalango.com.br', 'Calanguinho');
insert into fornecedores (razaoSocial, fantasia, cnpj, ie, cep, endereco, numero, complemento, bairro, cidade, uf, nomeContato, fone, whatsapp, email, site, obs) values ('Kabum', 'Kabum', '45.283.811/0059-76', '207292929111', '70713-000', 'Rua do Kabum', '3000', '', 'Cambuquira', 'Londrina', 'PR', 'Maria do Carmo', '3333-0000', '4444-0000', 'maria.carmo@kabum.com.br', 'www.kabum.com.br', 'Explosao');
insert into fornecedores (razaoSocial, fantasia, cnpj, ie, cep, endereco, numero, complemento, bairro, cidade, uf, nomeContato, fone, whatsapp, email, site, obs) values ('Furacão', 'Furacao', '46.283.811/0059-76', '206292929112', '80711-000', 'Rua das Explosões', '4000', '', 'Explosivo', 'Blumenau', 'SC', 'Carlos Magno', '5555-0000', '6666-0000', 'carlos.magno@furacao.com.br', 'www.furacao.com.br', 'Ventania');
insert into fornecedores (razaoSocial, fantasia, cnpj, ie, cep, endereco, numero, complemento, bairro, cidade, uf, nomeContato, fone, whatsapp, email, site, obs) values ('Fusca', 'Fusca', '47.283.811/0059-76', '206292929113', '90711-000', 'Rua dos carros', '5000', '', 'Fuscão Preto', 'Rocinha', 'RJ', 'Zé Pequeno', '7777-0000', '8888-0000', 'ze.pequeno@fusca.com.br', 'www.fusca.com.br', 'Vrum');
insert into fornecedores (razaoSocial, fantasia, cnpj, ie, cep, endereco, numero, complemento, bairro, cidade, uf, nomeContato, fone, whatsapp, email, site, obs) values ('Karamanhola', 'Karamanhola', '43.283.811/0059-75', '106292929110', '89011-000', 'Rua Karamanha', '9000', '', 'Asa Norte', 'Maranhãi', 'MA', 'Osmar', '1234-5678', '1234-0000', 'ma.carlos@email.com.br', 'www.kalunga.com.br', 'Pode pá');

select * from fornecedores;

-- pesquisa avançada com filtro 
select idFor as ID, fantasia as Fornecedor, fone, nomeContato as contato from
fornecedores where fantasia like ('k%');

/*
 Relacionamento de tabelas 1 - N (um para muitos)
 Chave estrangeira (FK) - (PK)
 idFor (chave estrangeira) usar mesmo nome e tipo de dados da chave
 primária (PK) sa tabela pai
*/
 
 -- timestamp default current_timestamp (obtém automaticamente a data e hora)
 -- date (tipo de dados relacionado a data)
 -- decimal(10,2) (tipo de dados relacionados a números não inteiros)
 -- decimal(10,2) (10 digitos com 2 casas decimais)
 
 create table produtos (
	codigo int primary key auto_increment,
    barcode varchar(255) unique,
    produto varchar(50) not null,
    descricao varchar(255),
    fabricante varchar(50) not null,
    datacad timestamp default current_timestamp,
    dataval date,
    estoque int not null,
    estoquemin int not null,
    unidade char(2) not null,
    localizacao varchar(50),
    custo decimal(10,2) not null,
    lucro decimal(10,2),
    idFor int not null,
    foreign key(idFor) references fornecedores(idFor)
);

describe produtos;

insert into produtos (barcode,produto,descricao,fabricante,dataval,estoque,
estoquemin,unidade,localizacao,custo,lucro,idFor)
values ('11111111','Caneta BIC azul','Caneta BIC cor azul, ponta fina CX 50',
'BIC',20231122,20,5,'CX','Prateleira 2',38.50,20,1);

insert into produtos (barcode,produto,descricao,fabricante,dataval,estoque,
estoquemin,unidade,localizacao,custo,lucro,idFor)
values ('22222222','Caneta BIC vermelha','Caneta BIC cor vermelho, ponta fina CX 25',
'BIC',20231122,3,5,'CX','Prateleira 3',18,20,1);

insert into produtos (barcode,produto,descricao,fabricante,dataval,estoque,
estoquemin,unidade,localizacao,custo,lucro,idFor)
values ('33333333','Cola bastão','Cola bastão pritt',
'Pritt',20221002,10,2,'UN','Prateleira 3',1.25,50,2);

insert into produtos (barcode,produto,descricao,fabricante,dataval,estoque,
estoquemin,unidade,localizacao,custo,lucro,idFor)
values ('44444444','Mouse logitech','Mouse usb logitech 2 botões',
'Logitech',20271122,10,15,'UN','Prateleira 3',18,30,4);

insert into produtos (barcode,produto,descricao,fabricante,dataval,estoque,
estoquemin,unidade,localizacao,custo,lucro,idFor)
values ('55555555','Régua 30cm','Régua de acrílico 30cm',
'Faber Castel',20251122,30,5,'UN','Prateleira 5',2.50,25,1);

select * from produtos;

/*
 Relatórios (select especial)
*/

-- relatório 1 (unificar produtos com fornecedores)
-- produtos.idFor (FK) fornecedores.idFor (PK)
select * from produtos inner join fornecedores
on produtos.idFor = fornecedores.idFor;

-- relatório 2 (fornecedor relacionado ao produto)
select
produtos.codigo as Código, produtos.produto,
fornecedores.fantasia as Fornecedor
from produtos
inner join fornecedores on produtos.idFor = fornecedores.idFor;

-- relatório 3 (inventário do estoque)
select sum(estoque * custo) as Total from produtos;

-- relatório 4 (calcular o preço de venda)
select codigo as código,produto,custo,
(custo + (custo * lucro)/100) as venda
from produtos;

-- relatório 5 (reposição de estoque)
-- %d/%m/%Y (dd/mm/aaaa) %d/%m/%y (dd/mm/aa) 
select codigo as código,produto,
date_format(dataval,'%d/%m/%Y') as data_validade,
estoque, estoquemin as estoque_mínimo
from produtos where estoque < estoquemin;

-- relatório 6 (produtos vencidos)
select codigo as código, produto, localizacao as localização,
date_format(dataval,'%d/%m/%Y') as data_validade,
datediff(dataval,curdate()) as dias_vencidos
from produtos where datediff(dataval,curdate()) < 0;



select * from clientes;

create table clientes (
idFor int primary key auto_increment,
Nome varchar(50) not null,
CPF varchar(20) unique,
Cep varchar(10) unique,
Endereco varchar(50) not null,
Numero varchar(6) not null,
Complemento varchar(20) not null,
Bairro varchar(50) not null,
Cidade varchar(50),
Uf char(2) not null,
Telefone varchar(15) not null,
Email varchar(50) not null
);


insert into clientes (Nome, CPF, Cep, Endereco, Numero, Complemento, Bairro, Cidade, Uf, Telefone,  Email) 
values ('Vitor','111.111.111-11','11111-11','TESTE','111','Teste','TESTE','São Paulo','SP','11111-1111','Teste@teste.com');


    

