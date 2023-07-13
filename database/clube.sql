CREATE SCHEMA IF NOT EXISTS clube3 DEFAULT CHARACTER SET utf8 ;
USE clube3 ;


-- socio
CREATE TABLE IF NOT EXISTS clube3.socio (
  idsocio INT NOT NULL AUTO_INCREMENT,
  socio_nome VARCHAR(45) NULL,
  socio_data_admissao DATE NULL,
  socio_num_identidade VARCHAR(45) NULL,
  socio_data_nacimento DATE NULL,
  socio_telefone VARCHAR(45) NULL,
  socio_endereco_rua VARCHAR(45) NULL,
  socio_endereco_cidade VARCHAR(45) NULL,
  socio_endereco_codigo_postal VARCHAR(45) NULL,
  PRIMARY KEY (idsocio)
);

-- recinto_desportivo
CREATE TABLE IF NOT EXISTS clube3.recinto_desportivo (
  idrecinto_desportivo INT NOT NULL AUTO_INCREMENT,
  recinto_desportivo_nome VARCHAR(45) NULL,
  PRIMARY KEY (idrecinto_desportivo)
);

-- pagamento
CREATE TABLE IF NOT EXISTS clube3.pagamento (
  idpagamento INT NOT NULL AUTO_INCREMENT,
  pagamento_data TIMESTAMP NULL,
  pagamento_valor DECIMAL(10) NULL,
  PRIMARY KEY (idpagamento)
);


-- reserva
CREATE TABLE IF NOT EXISTS clube3.reserva (
  idreserva INT NOT NULL AUTO_INCREMENT,
  res_data DATE NULL,
  recinto_desportivo_idrecinto_desportivo INT NOT NULL,
  socio_idsocio INT NOT NULL,
  pagamento_idpagamento INT NOT NULL,
  PRIMARY KEY (idreserva, recinto_desportivo_idrecinto_desportivo, socio_idsocio, pagamento_idpagamento),
    FOREIGN KEY (recinto_desportivo_idrecinto_desportivo)
    REFERENCES clube3.recinto_desportivo (idrecinto_desportivo),
    FOREIGN KEY (socio_idsocio)
    REFERENCES clube3.socio (idsocio),
    FOREIGN KEY (pagamento_idpagamento)
    REFERENCES clube3.pagamento (idpagamento)
);

-- modalidade
CREATE TABLE IF NOT EXISTS clube3.modalidade (
  idmodalidade INT NOT NULL AUTO_INCREMENT,
  modalidade_nome VARCHAR(45) NULL,
  modalidade_vagas INT NULL,
  PRIMARY KEY (idmodalidade)
);

-- inscreve_se
CREATE TABLE IF NOT EXISTS clube3.inscreve_se (
  idinscreve_se INT NOT NULL AUTO_INCREMENT,
  socio_idsocio INT NOT NULL,
  modalidade_idmodalidade INT NOT NULL,
  pagamento_idpagamento INT NOT NULL,
  PRIMARY KEY (idinscreve_se, socio_idsocio, modalidade_idmodalidade, pagamento_idpagamento),
  FOREIGN KEY (socio_idsocio)
    REFERENCES clube3.socio (idsocio),
  FOREIGN KEY (modalidade_idmodalidade)
    REFERENCES clube3.modalidade (idmodalidade),
  FOREIGN KEY (pagamento_idpagamento)
    REFERENCES clube3.pagamento (idpagamento)
);


-- querys socio
 INSERT INTO socio (socio_nome, socio_data_admissao, socio_num_identidade, socio_data_nacimento, socio_telefone, socio_endereco_rua, socio_endereco_cidade, socio_endereco_codigo_postal) 
 VALUES ('Juan Perez', '2021-01-01', '123456789', '1990-05-10', '123456789', 'Rua A', 'Ciudad A', '12345'),
 ('Maria Sanchez', '2021-02-02', '987654321', '1992-08-15', '987654321', 'Rua B', 'Ciudad B', '54321'),
 ('Pedro Gomez', '2021-03-03', '456789123', '1988-12-20', '456789123', 'Rua C', 'Ciudad C', '98765'),
 ('Ana Torres', '2021-04-04', '321654987', '1995-03-25', '321654987', 'Rua D', 'Ciudad D', '56789'),
 ('Luis Ramirez', '2021-05-05', '789123456', '1998-07-05', '789123456', 'Rua E', 'Ciudad E', '24680');
 
 
UPDATE socio
SET socio_telefone = '999999999'
WHERE idsocio = 6;

UPDATE socio
SET socio_endereco = 'Novo endereco', endereco_idendereco = 1
WHERE idsocio = 7;

ALTER TABLE socio
ADD socio_email VARCHAR(100) NULL;

ALTER TABLE socio
MODIFY COLUMN socio_num_identidade VARCHAR(45) NOT NULL;

DELETE FROM socio
WHERE idsocio = 9;

SELECT s.socio_nome, e.endereco_cidade
FROM socio s
JOIN endereco e ON s.endereco_idendereco = e.idendereco;

SELECT *
FROM socio
WHERE socio_nome LIKE '%San%';

SELECT *
FROM socio
ORDER BY socio_data_admissao ASC;

SELECT endereco_idendereco, endereco_cidade, COUNT(*) AS total_socios
FROM socio
JOIN endereco  ON endereco_idendereco = idendereco
GROUP BY endereco_idendereco
HAVING total_socios > 1;

-- querys socio
 INSERT INTO recinto_desportivo (recinto_desportivo_nome) 
 VALUES('Recinto 1'),
 ('Recinto 2'),
 ('Recinto 3'),
 ('Recinto 4'),
 ('Recinto 5');
 
UPDATE recinto_desportivo
SET recinto_desportivo_nome = 'Novo Recinto'
WHERE idrecinto_desportivo = 1;

DELETE FROM recinto_desportivo WHERE idrecinto_desportivo = 3;

UPDATE recinto_desportivo
SET recinto_desportivo_nome = 'Novo Nome', recinto_desportivo_capacidade = 200
WHERE idrecinto_desportivo = 4;

ALTER TABLE recinto_desportivo
ADD COLUMN recinto_desportivo_capacidade int;

ALTER TABLE recinto_desportivo
ADD recinto_desportivo_endereco VARCHAR(100) NULL;

DELETE FROM recinto_desportivo
WHERE idrecinto_desportivo = 2;

SELECT r.recinto_desportivo_nome, r.recinto_desportivo_capacidade, re.endereco_cidade
FROM recinto_desportivo r
JOIN endereco re ON r.idrecinto_desportivo = re.idendereco;

SELECT *
FROM recinto_desportivo
WHERE recinto_desportivo_nome LIKE '%Recinto%';

SELECT *
FROM recinto_desportivo
ORDER BY recinto_desportivo_capacidade DESC;


SELECT recinto_desportivo_nome, COUNT(*) AS total_reservas
FROM recinto_desportivo
JOIN reserva ON recinto_desportivo.idrecinto_desportivo = reserva.recinto_desportivo_idrecinto_desportivo
GROUP BY recinto_desportivo_nome
HAVING total_reservas > 0;
 
 -- querys pagamento
 INSERT INTO pagamento (pagamento_data, pagamento_valor) 
 VALUES('2021-01-01 09:00:00', 100.00),
 ('2021-02-02 10:00:00', 150.00),
 ('2021-03-03 11:00:00', 200.00),
 ('2021-04-04 12:00:00', 120.00),
 ('2021-05-05 13:00:00', 180.00);
 
UPDATE pagamento
SET pagamento_valor = 90.00
WHERE idpagamento = 1;

DELETE FROM pagamento WHERE idpagamento = 3;
UPDATE pagamento
SET pagamento_data = '2021-07-10 15:00:00'
WHERE idpagamento = 4;

ALTER TABLE pagamento
ADD pagamento_tipo VARCHAR(45) NULL;

ALTER TABLE pagamento
MODIFY COLUMN pagamento_valor DECIMAL(10,2) NOT NULL;

DELETE FROM pagamento
WHERE idpagamento = 2;

SELECT p.pagamento_valor, r.res_data, s.socio_nome
FROM pagamento p
JOIN reserva r ON p.idpagamento = r.pagamento_idpagamento
JOIN socio s ON r.socio_idsocio = s.idsocio;

SELECT *
FROM pagamento
WHERE pagamento_tipo LIKE '%Efectivo%';

SELECT *
FROM pagamento
ORDER BY pagamento_valor DESC;

SELECT COUNT(*) AS total_pagamentos, AVG(pagamento_valor) AS media_pagamentos
FROM pagamento;

 select * from socio;
-- querys reserva
INSERT INTO reserva (res_data, recinto_desportivo_idrecinto_desportivo, socio_idsocio, pagamento_idpagamento)
VALUES ('2021-08-01', 4, 1, 1),
('2020-08-01', 1, 2, 2),
('2023-08-01', 1, 3, 3),
('2022-08-01', 3, 4, 4),
('2021-03-01', 3, 5, 5);

UPDATE clube.reserva
SET res_data = '2023-07-15'
WHERE idreserva = 1;

UPDATE reserva
SET recinto_desportivo_idrecinto_desportivo = 6
WHERE idreserva = 2;

ALTER TABLE reserva
ADD res_status VARCHAR(50) NULL;

ALTER TABLE reserva
MODIFY COLUMN res_data DATETIME NULL;

SELECT r.res_data, rd.recinto_desportivo_nome, s.socio_nome
FROM reserva r
JOIN recinto_desportivo rd ON r.recinto_desportivo_idrecinto_desportivo = rd.idrecinto_desportivo
JOIN socio s ON r.socio_idsocio = s.idsocio;

SELECT *
FROM reserva
WHERE res_data LIKE '2021-08%';

SELECT *
FROM reserva
ORDER BY res_data DESC;

SELECT COUNT(*) AS total_reservas, MAX(pagamento_valor) AS max_valor_pagamento
FROM reserva
JOIN pagamento ON reserva.pagamento_idpagamento = pagamento.idpagamento
GROUP BY reserva.pagamento_idpagamento;

-- querys modalidede
 INSERT INTO modalidade (modalidade_nome, modalidade_vagas) 
 VALUES ('Modalidade 1', 10),
 ('Modalidade 2', 15),
 ('Modalidade 3', 20),
 ('Modalidade 4', 12),
 ('Modalidade 5', 18);
 
UPDATE modalidade
SET modalidade_vagas = 25
WHERE idmodalidade = 1;

UPDATE modalidade
SET modalidade_nome = 'Nova Modalidade'
WHERE idmodalidade = 2;

ALTER TABLE modalidade
ADD modalidade_descricao VARCHAR(100) NULL;

ALTER TABLE modalidade
DROP COLUMN modalidade_vagas;

ALTER TABLE modalidade
ADD COLUMN modalidade_vagas int;

DELETE FROM modalidade
WHERE idmodalidade = 3;

SELECT *
FROM modalidade
WHERE modalidade_nome LIKE '%dal%';

SELECT *
FROM modalidade
ORDER BY modalidade_vagas DESC;

SELECT COUNT(*) AS total_modalidades, MAX(modalidade_vagas) AS max_vagas
FROM modalidade;
select * from socio;
-- querys inscreve_se
INSERT INTO inscreve_se (socio_idsocio, modalidade_idmodalidade, pagamento_idpagamento)
VALUES (1, 1, 1),
       (2, 2, 2),
       (3, 3, 3),
       (4, 4, 4),
       (5, 5, 5);
       
UPDATE inscreve_se
SET modalidade_idmodalidade = 5
WHERE idinscreve_se = 1;

UPDATE inscreve_se
SET socio_idsocio = 7
WHERE idinscreve_se = 2;

ALTER TABLE inscreve_se
ADD inscreve_se_status VARCHAR(50) NULL;

ALTER TABLE inscreve_se
ADD inscreve_se_descricao VARCHAR(50) NULL;

DELETE FROM inscreve_se
WHERE idinscreve_se = 3;

SELECT i.idinscreve_se, s.socio_nome, m.modalidade_nome
FROM inscreve_se i
JOIN socio s ON i.socio_idsocio = s.idsocio
JOIN modalidade m ON i.modalidade_idmodalidade = m.idmodalidade;

SELECT  *
FROM inscreve_se
JOIN modalidade ON inscreve_se.modalidade_idmodalidade = modalidade.idmodalidade
WHERE modalidade.modalidade_nome LIKE '%_M%';

SELECT *
FROM inscreve_se
ORDER BY socio_idsocio ASC;

SELECT modalidade_idmodalidade, COUNT(*) AS total_inscricoes
FROM inscreve_se
GROUP BY modalidade_idmodalidade
HAVING total_inscricoes >= 1;

SET FOREIGN_KEY_CHECKS=0;
