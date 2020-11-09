package br.com.alura.funcionarios.api.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import br.com.alura.funcionarios.api.dto.UnidadeDTO;
import br.com.alura.funcionarios.api.entity.Unidade;
import br.com.alura.funcionarios.api.exception.UnidadeNaoEncontradaException;
import br.com.alura.funcionarios.api.repository.UnidadeRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UnidadeService {

	private final UnidadeRepository unidadeRepository;

	private final ModelMapper modelMapper;

	public List<UnidadeDTO> listAll() {
		List<Unidade> unidades = unidadeRepository.findAll();
		return unidades
				.stream()
				.map(unidade -> modelMapper.map(unidade, UnidadeDTO.class))
				.collect(Collectors.toList());
	}

	public UnidadeDTO criar(UnidadeDTO unidadeDTO) {
		Unidade unidade = modelMapper.map(unidadeDTO, Unidade.class);
		return modelMapper.map(unidadeRepository.save(unidade), UnidadeDTO.class);
	}

	public void remover(Long id) {
		try {
			checkIfExistsAndReturn(id);
		}
		catch (UnidadeNaoEncontradaException e) {
		}

		unidadeRepository.deleteById(id);
	}

	public UnidadeDTO atualizar(Long id, @Valid UnidadeDTO unidadeDTO) throws UnidadeNaoEncontradaException {
		Unidade unidade = checkIfExistsAndReturn(id);
		unidade.setDescricao(unidadeDTO.getDescricao());
		unidade.setEndereco(unidadeDTO.getEndereco());

		return modelMapper.map(unidadeRepository.save(unidade), UnidadeDTO.class);
	}

	private Unidade checkIfExistsAndReturn(Long id) throws UnidadeNaoEncontradaException {
		Optional<Unidade> optional = unidadeRepository.findById(id);
		if (!optional.isPresent()) {
			throw new UnidadeNaoEncontradaException(id);
		}
		return optional.get();
	}

	public List<Unidade> findByIdIn(List<Long> unidades) {
		return unidadeRepository.findByIdIn(unidades);
	}

}
