package br.com.alura.funcionarios.api.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.com.alura.funcionarios.api.dto.FuncionarioDTO;
import br.com.alura.funcionarios.api.dto.NovoFuncionarioDTO;
import br.com.alura.funcionarios.api.entity.Cargo;
import br.com.alura.funcionarios.api.entity.Funcionario;
import br.com.alura.funcionarios.api.entity.FuncionarioProjecao;
import br.com.alura.funcionarios.api.entity.Unidade;
import br.com.alura.funcionarios.api.exception.CargoNaoEncontradoException;
import br.com.alura.funcionarios.api.exception.FuncionarioComCpfJaCadastrado;
import br.com.alura.funcionarios.api.exception.FuncionarioNaoEncontradoException;
import br.com.alura.funcionarios.api.exception.UnidadeNaoEncontradaException;
import br.com.alura.funcionarios.api.repository.FuncionarioRepository;
import br.com.alura.funcionarios.api.specification.FuncionarioSpecificationBuilder;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FuncionarioService {

	private final FuncionarioRepository funcionarioRepository;

	private final CargoService cargoService;

	private final UnidadeService unidadeService;

	private final ModelMapper modelMapper;

	public Page<FuncionarioDTO> listAll(Optional<String> nome, Optional<String> cpf, Optional<String> salario,
			Optional<String> dataContratacao, Pageable pageable) {
		Specification<Funcionario> spec = FuncionarioSpecificationBuilder.builder()
				.cpf(cpf)
				.salario(salario)
				.nome(nome)
				.dataContratacao(dataContratacao)
				.build();
		
		Page<Funcionario> funcionarios = funcionarioRepository.findAll(spec, pageable);
		
		return new PageImpl<>(
				funcionarios.stream().map(funcionario -> modelMapper.map(funcionario, FuncionarioDTO.class))
						.collect(Collectors.toList()));
	}

	public FuncionarioDTO criar(NovoFuncionarioDTO funcionarioDTO)
			throws CargoNaoEncontradoException, UnidadeNaoEncontradaException, FuncionarioComCpfJaCadastrado {
		if (funcionarioRepository.existsByCpf(funcionarioDTO.getCpf())) {
			throw new FuncionarioComCpfJaCadastrado(funcionarioDTO.getCpf());
		}

		List<Long> ids = funcionarioDTO.getUnidades()
				.stream().map(u -> u.getId())
				.collect(Collectors.toList());

		List<Unidade> unidades = unidadeService.findByIdIn(ids);

		if (unidades.isEmpty()) {
			throw new UnidadeNaoEncontradaException();
		}

		Cargo cargo = cargoService.findById(funcionarioDTO.getCargo());
		Funcionario funcionario = modelMapper.map(funcionarioDTO, Funcionario.class);
		funcionario.setCargo(cargo);
		funcionario.setUnidades(unidades);

		return modelMapper.map(funcionarioRepository.save(funcionario), FuncionarioDTO.class);
	}

	public void remover(Long id) {
		try {
			checkIfExistsAndReturn(id);
		}
		catch (FuncionarioNaoEncontradoException e) {
		}

		funcionarioRepository.deleteById(id);
	}

	public FuncionarioDTO atualizar(Long id, @Valid FuncionarioDTO funcionarioDTO)
			throws FuncionarioNaoEncontradoException {
		Funcionario funcionario = checkIfExistsAndReturn(id);
		funcionario.setCargo(modelMapper.map(funcionarioDTO.getCargo(), Cargo.class));
		funcionario.setCpf(funcionarioDTO.getCpf());
		funcionario.setDataContratacao(funcionarioDTO.getDataContratacao());
		funcionario.setNome(funcionarioDTO.getNome());
		funcionario.setSalario(funcionarioDTO.getSalario());

		return modelMapper.map(funcionarioRepository.save(funcionario), FuncionarioDTO.class);
	}

	private Funcionario checkIfExistsAndReturn(Long id) throws FuncionarioNaoEncontradoException {
		Optional<Funcionario> optional = funcionarioRepository.findById(id);
		if (!optional.isPresent()) {
			throw new FuncionarioNaoEncontradoException(id);
		}
		return optional.get();
	}

	public Page<FuncionarioDTO> listByNomeSalarioMaiorData(String nome, BigDecimal salario, LocalDate dataContratacao,
			Pageable pageable) {
		Page<Funcionario> funcionarios = funcionarioRepository.findByNomeSalarioMaiorDataContratacao(nome, salario,
				dataContratacao, pageable);
		return new PageImpl<>(funcionarios
				.stream()
				.map(funcionario -> modelMapper.map(funcionario, FuncionarioDTO.class))
				.collect(Collectors.toList()));
	}

	public Page<FuncionarioDTO> listByDataContratacaoMaiorOuIgualQue(LocalDate dataContratacao, Pageable pageable) {
		Page<Funcionario> funcionarios = funcionarioRepository.findByDataContratacaoMaiorOuIqualQue(dataContratacao,
				pageable);
		return new PageImpl<>(funcionarios
				.stream()
				.map(funcionario -> modelMapper.map(funcionario, FuncionarioDTO.class))
				.collect(Collectors.toList()));
	}

	public Page<FuncionarioProjecao> listFuncionariosSalarios(Pageable pageable) {
		return funcionarioRepository.listFuncionariosSalarios(pageable);
	}

}
