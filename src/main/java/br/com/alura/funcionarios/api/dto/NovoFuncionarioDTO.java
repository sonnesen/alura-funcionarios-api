package br.com.alura.funcionarios.api.dto;

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
public class NovoFuncionarioDTO {

	@NotBlank
	@Size(max = 255)
	private String nome;

	@NotBlank
	@Size(max = 14)
	private String cpf;

	@NotNull
	private String salario;

	@NotNull
	private String dataContratacao;
	
	@NotNull
	private Long cargo;
	
	@NotNull
	@Size(min = 1)
	private List<NovaUnidadeDTO> unidades;
}
