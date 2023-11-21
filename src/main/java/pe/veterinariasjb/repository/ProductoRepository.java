package pe.veterinariasjb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.veterinariasjb.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {

}
