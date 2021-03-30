-- Arquivo dos comandos SQL executados no BD mysql

CREATE database digital_innovation_one;
USE digital_innovation_one;

CREATE TABLE aluno (
	id INTEGER PRIMARY KEY auto_increment,
    nome varchar(80) NOT NULL,
    idade INTEGER NOT NULL,
    estado CHARACTER(2) NOT NULL
    );

insert INTO aluno(nome, idade, estado) VALUES ('Pedro', 20, 'RJ');
insert INTO aluno(nome, idade, estado) VALUES ('Maria', 35, 'AC');
insert INTO aluno(nome, idade, estado) VALUES ('Pedro', 19, 'SC');
insert INTO aluno(nome, idade, estado) VALUES ('Pedro', 51, 'GO');

CREATE TABLE curso (
id INTEGER PRIMARY KEY auto_increment,
nome VARCHAR(80),
duracao_horas time
);

INSERT INTO curso(id, nome, duracao_horas) VALUES (1, 'Maria', '02:05:00');
INSERT INTO curso(id, nome, duracao_horas) VALUES (2, 'Pedro', '01:30:10');
INSERT INTO curso(id, nome, duracao_horas) VALUES (3, 'Joao', '10:40:15');
INSERT INTO curso(id, nome, duracao_horas) VALUES (4, 'Jose', '20:00:20');
INSERT INTO curso(id, nome, duracao_horas) VALUES (5, 'Luiz', '15:01:10');

CREATE TABLE alunoJPA (
	id INTEGER PRIMARY KEY auto_increment,
    nome varchar(80) NOT NULL,
    idade INTEGER NOT NULL,
    estado_id INTEGER NOT NULL,
    FOREIGN KEY(estado_id) REFERENCES estadoJPA(id)
    );

insert INTO alunoJPA(id, nome, idade, estado_id) VALUES (1, 'Daniel', 29, 1);
insert INTO alunoJPA(id, nome, idade, estado_id) VALUES (2, 'Joao', 20, 1);
insert INTO alunoJPA(id, nome, idade, estado_id) VALUES (3, 'Pedro', 30, 1);

CREATE TABLE estadoJPA (
    id INTEGER PRIMARY KEY auto_increment,
    nome VARCHAR(80) NOT NULL,
    sigla VARCHAR(2) NOT NULL
    );

insert into estadoJPA(id, nome, sigla) VALUES (1, 'Rio de Janeiro', 'RJ');
insert into estadoJPA(id, nome, sigla) VALUES (2, 'Sao Paulo', 'SP');