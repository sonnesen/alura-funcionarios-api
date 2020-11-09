package br.com.alura.funcionarios.api.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.funcionarios.api.dto.UnidadeDTO;
import br.com.alura.funcionarios.api.exception.UnidadeNaoEncontradaException;
import br.com.alura.funcionarios.api.service.UnidadeService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/unidades")
@RequiredArgsConstructor
public class UnidadeResource {

	private final UnidadeService unidadeService;

	@GetMapping
	public List<UnidadeDTO> listAll() {
		return unidadeService.listAll();
	}

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public UnidadeDTO criar(@RequestBody @Valid UnidadeDTO unidadeDTO) {
		return unidadeService.criar(unidadeDTO);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		unidadeService.remover(id);
	}

	@PutMapping("/{id}")
	public UnidadeDTO atualizar(@PathVariable Long id, @RequestBody @Valid UnidadeDTO unidadeDTO)
			throws UnidadeNaoEncontradaException {
		return unidadeService.atualizar(id, unidadeDTO);
	}
}
