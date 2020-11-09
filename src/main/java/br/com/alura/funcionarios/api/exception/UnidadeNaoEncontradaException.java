package br.com.alura.funcionarios.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class UnidadeNaoEncontradaException extends Exception {

	private static final long serialVersionUID = 1L;

	public UnidadeNaoEncontradaException(Long id) {
		super(String.format("Unidade de trabalho %s não encontrada!", id));
	}

	public UnidadeNaoEncontradaException() {
		super("Unidade de trabalho não encontrada!");
	}

}
