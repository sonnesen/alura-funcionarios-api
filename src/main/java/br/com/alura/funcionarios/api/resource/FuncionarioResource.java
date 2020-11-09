package br.com.alura.funcionarios.api.resource;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.funcionarios.api.dto.FuncionarioDTO;
import br.com.alura.funcionarios.api.dto.NovoFuncionarioDTO;
import br.com.alura.funcionarios.api.entity.FuncionarioProjecao;
import br.com.alura.funcionarios.api.exception.CargoNaoEncontradoException;
import br.com.alura.funcionarios.api.exception.FuncionarioComCpfJaCadastrado;
import br.com.alura.funcionarios.api.exception.FuncionarioNaoEncontradoException;
import br.com.alura.funcionarios.api.exception.UnidadeNaoEncontradaException;
import br.com.alura.funcionarios.api.service.FuncionarioService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/funcionarios")
@RequiredArgsConstructor
public class FuncionarioResource {

	private final FuncionarioService funcionarioService;

	@GetMapping
	public Page<FuncionarioDTO> listAll(@RequestParam Optional<String> nome,
			@RequestParam Optional<String> cpf,
			@RequestParam Optional<String> salario,
			@RequestParam Optional<String> dataContratacao,
			Pageable pageable) {
		return funcionarioService.listAll(nome, cpf, salario, dataContratacao, pageable);
	}

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public FuncionarioDTO criar(@RequestBody @Valid NovoFuncionarioDTO funcionarioDTO)
			throws CargoNaoEncontradoException, UnidadeNaoEncontradaException, FuncionarioComCpfJaCadastrado {
		return funcionarioService.criar(funcionarioDTO);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		funcionarioService.remover(id);
	}

	@PutMapping("/{id}")
	public FuncionarioDTO atualizar(@PathVariable Long id, @RequestBody @Valid FuncionarioDTO funcionarioDTO)
			throws FuncionarioNaoEncontradoException {
		return funcionarioService.atualizar(id, funcionarioDTO);
	}

	@GetMapping("/salarios")
	public Page<FuncionarioProjecao> listFuncionariosSalarios(Pageable pageable) {
		return funcionarioService.listFuncionariosSalarios(pageable);
	}

}
