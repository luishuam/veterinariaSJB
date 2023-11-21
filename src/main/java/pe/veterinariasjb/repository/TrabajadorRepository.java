package pe.veterinariasjb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.veterinariasjb.model.Trabajador;

public interface TrabajadorRepository extends JpaRepository<Trabajador, Integer> {

}