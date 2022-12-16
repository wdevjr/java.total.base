﻿--
-- Script was generated by Devart dbForge Studio 2022 for MySQL, Version 9.1.21.0
-- Product home page: http://www.devart.com/dbforge/mysql/studio
-- Script date 12/12/2022 14:05:58
-- Server version: 8.0.28
-- Client version: 4.1
--

-- 
-- Disable foreign keys
-- 
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;

-- 
-- Set SQL mode
-- 
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 
-- Set character set the client will use to send SQL statements to the server
--
SET NAMES 'utf8';

DROP DATABASE IF EXISTS ebookmvc;

CREATE DATABASE ebookmvc
CHARACTER SET utf8
COLLATE utf8_unicode_ci;

--
-- Set default database
--
USE ebookmvc;

--
-- Create table `usuario`
--
CREATE TABLE usuario (
  id bigint NOT NULL AUTO_INCREMENT,
  login varchar(255) DEFAULT NULL,
  nome varchar(255) DEFAULT NULL,
  senha varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
)
ENGINE = INNODB,
AUTO_INCREMENT = 2,
CHARACTER SET utf8,
COLLATE utf8_unicode_ci;

--
-- Create table `produto`
--
CREATE TABLE produto (
  id bigint NOT NULL AUTO_INCREMENT,
  nome varchar(255) NOT NULL,
  preco_custo decimal(10, 2) NOT NULL,
  quantidade_estoque int NOT NULL,
  PRIMARY KEY (id),
  INDEX IDX_produto_id (id)
)
ENGINE = INNODB,
AUTO_INCREMENT = 6,
AVG_ROW_LENGTH = 8192,
CHARACTER SET utf8,
COLLATE utf8_unicode_ci;

-- 
-- Dumping data for table usuario
--
INSERT INTO usuario VALUES
(1, 'warhjr', 'Walter Junior', 'senhah');

-- 
-- Dumping data for table produto
--
INSERT INTO produto VALUES
(1, 'Tigela de Vidro', 120.00, 10),
(2, 'Panela de Tramontina Us', 45.00, 10),
(3, 'Pratos', 12.00, 5),
(4, 'Impressora HP', 299.99, 10),
(5, 'Potes de Vidro', 12.00, 80);

-- 
-- Restore previous SQL mode
-- 
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;

-- 
-- Enable foreign keys
-- 
/*!40014 SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS */;