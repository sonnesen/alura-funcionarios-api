package br.com.alura.funcionarios.api.specification;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import br.com.alura.funcionarios.api.entity.Funcionario;

public class FuncionarioSpecificationBuilder {

	private Optional<String> nome;

	private Optional<String> cpf;

	private Optional<String> salario;

	private Optional<String> dataContratacao;

	public FuncionarioSpecificationBuilder nome(Optional<String> nome) {
		this.nome = nome;
		return this;
	}

	public FuncionarioSpecificationBuilder cpf(Optional<String> cpf) {
		this.cpf = cpf;
		return this;
	}

	public FuncionarioSpecificationBuilder salario(Optional<String> salario) {
		this.salario = salario;
		return this;
	}

	public FuncionarioSpecificationBuilder dataContratacao(Optional<String> dataContratacao) {
		this.dataContratacao = dataContratacao;
		return this;
	}

	public Specification<Funcionario> build() {
		return (root, criteriaQuery, criteriaBuilder) -> {
			List<Predicate> predicates = new ArrayList<>();

			if (nome.isPresent())
				predicates.add(criteriaBuilder.like(root.get("nome"), String.format("%%%s%%", nome.get())));

			if (cpf.isPresent())
				predicates.add(criteriaBuilder.equal(root.get("cpf"), cpf.get()));

			if (salario.isPresent())
				predicates.add(criteriaBuilder.greaterThan(root.get("salario"), new BigDecimal(salario.get())));

			if (dataContratacao.isPresent())
				predicates.add(criteriaBuilder.greaterThan(root.get("dataContratacao"),
						LocalDate.parse(dataContratacao.get())));

			return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
		};
	}

	public static FuncionarioSpecificationBuilder builder() {
		return new FuncionarioSpecificationBuilder();
	}

}
