package br.com.alura.funcionarios.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class FuncionarioComCpfJaCadastrado extends Exception {

	private static final long serialVersionUID = 1L;

	public FuncionarioComCpfJaCadastrado(String cpf) {
		super(String.format("Funcionário com CPF %s já cadastrado!", cpf));
	}

}
