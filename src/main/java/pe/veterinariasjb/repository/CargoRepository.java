package pe.veterinariasjb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.veterinariasjb.model.Cargo;

public interface CargoRepository extends JpaRepository<Cargo, Integer> {

}
