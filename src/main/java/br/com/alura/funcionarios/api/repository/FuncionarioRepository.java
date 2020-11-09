package br.com.alura.funcionarios.api.repository;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.alura.funcionarios.api.entity.Funcionario;
import br.com.alura.funcionarios.api.entity.FuncionarioProjecao;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>,
		JpaSpecificationExecutor<Funcionario> {

	Page<Funcionario> findByNomeLike(String nome, Pageable pageable);

	Boolean existsByCpf(String cpf);

	@Query("SELECT f FROM Funcionario f "
			+ "WHERE f.nome like %:nome% "
			+ "  AND f.salario >= :salario "
			+ "  AND f.dataContratacao = :dataContratacao")
	Page<Funcionario> findByNomeSalarioMaiorDataContratacao(String nome,
			BigDecimal salario, LocalDate dataContratacao, Pageable pageable);

	@Query(value = "SELECT * FROM funcionarios f "
			+ "WHERE f.data_contratacao >= :dataContratacao", nativeQuery = true)
	Page<Funcionario> findByDataContratacaoMaiorOuIqualQue(LocalDate dataContratacao,
			Pageable pageable);

	@Query(value = "SELECT f.id, f.nome, f.salario FROM funcionarios f", nativeQuery = true)
	Page<FuncionarioProjecao> listFuncionariosSalarios(Pageable pageable);
}
