package pe.veterinariasjb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.veterinariasjb.model.Mascota;

public interface MascotaRepository extends JpaRepository<Mascota, Integer> {

}
