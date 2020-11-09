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

import br.com.alura.funcionarios.api.dto.CargoDTO;
import br.com.alura.funcionarios.api.exception.CargoNaoEncontradoException;
import br.com.alura.funcionarios.api.service.CargoService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/cargos")
@RequiredArgsConstructor
public class CargoResource {

	private final CargoService cargoService;

	@GetMapping
	public List<CargoDTO> listAll() {
		return cargoService.listAll();
	}

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public CargoDTO criar(@RequestBody @Valid CargoDTO cargoDTO) {
		return cargoService.criar(cargoDTO);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		cargoService.remover(id);
	}
	
	@PutMapping("/{id}")
	public CargoDTO atualizar(@PathVariable Long id, @RequestBody @Valid CargoDTO cargoDTO) throws CargoNaoEncontradoException {
		return cargoService.atualizar(id, cargoDTO);
	}
}
