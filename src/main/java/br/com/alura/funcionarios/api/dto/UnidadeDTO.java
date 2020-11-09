package br.com.alura.funcionarios.api.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UnidadeDTO {

	private Long id;
	
	@NotBlank
	@Size(max = 255)
	private String descricao;

	@NotBlank
	@Size(max = 255)
	private String endereco;
}
