package pe.veterinariasjb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.veterinariasjb.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
