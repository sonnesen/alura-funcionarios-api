package br.com.alura.funcionarios.api.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import br.com.alura.funcionarios.api.dto.CargoDTO;
import br.com.alura.funcionarios.api.entity.Cargo;
import br.com.alura.funcionarios.api.exception.CargoNaoEncontradoException;
import br.com.alura.funcionarios.api.repository.CargoRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CargoService {

	private final CargoRepository cargoRepository;

	private final ModelMapper modelMapper;

	public List<CargoDTO> listAll() {
		List<Cargo> cargos = cargoRepository.findAll();
		return cargos
				.stream()
				.map(cargo -> modelMapper.map(cargo, CargoDTO.class))
				.collect(Collectors.toList());
	}

	public CargoDTO criar(CargoDTO cargoDTO) {
		Cargo cargo = modelMapper.map(cargoDTO, Cargo.class);
		return modelMapper.map(cargoRepository.save(cargo), CargoDTO.class);
	}

	public void remover(Long id) {
		try {
			checkIfExistsAndReturn(id);
		}
		catch (CargoNaoEncontradoException e) {
		}

		cargoRepository.deleteById(id);
	}

	public CargoDTO atualizar(Long id, @Valid CargoDTO cargoDTO) throws CargoNaoEncontradoException {
		Cargo cargo = checkIfExistsAndReturn(id);
		cargo.setDescricao(cargoDTO.getDescricao());

		return modelMapper.map(cargoRepository.save(cargo), CargoDTO.class);
	}

	private Cargo checkIfExistsAndReturn(Long id) throws CargoNaoEncontradoException {
		Optional<Cargo> optional = cargoRepository.findById(id);
		if (!optional.isPresent()) {
			throw new CargoNaoEncontradoException(id);
		}
		return optional.get();
	}

	public Cargo findById(Long id) throws CargoNaoEncontradoException {
		return checkIfExistsAndReturn(id);
	}

}
