package br.com.alura.funcionarios.api.entity;

import java.math.BigDecimal;

public interface FuncionarioProjecao {

	Long getId();

	String getNome();

	BigDecimal getSalario();
}
