package pe.veterinariasjb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.veterinariasjb.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Usuario findByUsuarioAndClave(String usua, String clave);
}
