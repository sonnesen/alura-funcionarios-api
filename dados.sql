INSERT INTO aluradb.cargos (id, descricao) VALUES
     (1, 'SUPERVIDOR DE VENDAS'),
     (2, 'GERENTE DE RH'),
     (3, 'COORDENADOR DE MARKETING'),
     (4, 'GERENTE FINANCEIRO'),
     (5, 'GERENTE DE TI');

INSERT INTO aluradb.unidades (id, descricao,endereco) VALUES
     (1, 'Unidade Sul','Rua São Luiz, 2379 Industrial Manuaus AM'),
     (2, 'Unidade Centro','Rua São João, 1234 Industrial São Paulo SP');
     
INSERT INTO aluradb.funcionarios (id, cpf,data_contratacao,nome,salario,id_cargo) VALUES
     (1, '12345678910','2020-11-05','José da Silva',5500.00,1),
     (2, '12345678920','2020-10-05','João de Souza',12000.00,2),
     (3, '12345678930','2019-01-05','Fulano de Tal',8000.00,3),
     (4, '12345678940','2018-04-10','Beltrano da Costa',9000.00,4),
     (5, '12345678950','2020-12-05','José de Souza',15000.00,5);
     
INSERT INTO aluradb.funcionarios_unidades (id_funcionario,id_unidade) VALUES
     (1,1),
     (4,1),
     (5,1),
     (5,2),
     (3,1),
     (3,2);