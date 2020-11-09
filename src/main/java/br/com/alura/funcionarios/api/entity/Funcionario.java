package br.com.alura.funcionarios.api.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "funcionarios")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Funcionario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 255, nullable = false)
	private String nome;

	@Column(unique = true, length = 14, nullable = false)
	private String cpf;

	@Column(nullable = false)
	private BigDecimal salario;

	@Column(name = "data_contratacao", nullable = false)
	private LocalDate dataContratacao;

	@ManyToOne
	@JoinColumn(name = "id_cargo", nullable = false)
	private Cargo cargo;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "funcionarios_unidades", joinColumns = {
			@JoinColumn(name = "id_funcionario") }, inverseJoinColumns = {
					@JoinColumn(name = "id_unidade") })
	private List<Unidade> unidades;
}
