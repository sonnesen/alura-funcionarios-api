package br.com.alura.funcionarios.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.funcionarios.api.entity.Cargo;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, Long> {

}
