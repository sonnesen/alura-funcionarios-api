package br.com.alura.funcionarios.api.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FuncionarioDTO {

	private Long id;
	
	@NotBlank
	@Size(max = 255)
	private String nome;

	@NotBlank
	@Size(max = 14)
	private String cpf;

	@NotNull
	private BigDecimal salario;

	@NotNull
	private LocalDate dataContratacao;
	
	@NotNull
	private CargoDTO cargo;
	
	@NotNull
	@Size(min = 1)
	private List<UnidadeDTO> unidades;
}
