CREATE TABLE `funcionarios_unidades` (
  `id_funcionario` bigint(20) NOT NULL,
  `id_unidade` bigint(20) NOT NULL,
  KEY `FK_funcionarios_unidades_unidade` (`id_unidade`),
  KEY `FK_funcionarios_unidades_funcionario` (`id_funcionario`),
  CONSTRAINT `FK_funcionarios_unidades_funcionario` FOREIGN KEY (`id_funcionario`) REFERENCES `funcionarios` (`id`),
  CONSTRAINT `FK_funcionarios_unidades_unidade` FOREIGN KEY (`id_unidade`) REFERENCES `unidades` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;