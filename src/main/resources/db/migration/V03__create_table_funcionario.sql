CREATE TABLE `funcionarios` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cpf` varchar(14) NOT NULL,
  `data_contratacao` date NOT NULL,
  `nome` varchar(255) NOT NULL,
  `salario` decimal(19,2) NOT NULL,
  `id_cargo` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `funcionarios_UN` (`cpf`),
  KEY `FK_funcionarios_cargos` (`id_cargo`),
  CONSTRAINT `FK_funcionarios_cargos` FOREIGN KEY (`id_cargo`) REFERENCES `cargos` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
