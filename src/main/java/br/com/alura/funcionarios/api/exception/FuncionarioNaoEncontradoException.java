package br.com.alura.funcionarios.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class FuncionarioNaoEncontradoException extends Exception {

	private static final long serialVersionUID = 1L;

	public FuncionarioNaoEncontradoException(Long id) {
		super(String.format("Cargo %s não encontrado!", id));
	}

}
