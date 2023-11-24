package pe.veterinariasjb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.AllArgsConstructor;
import pe.veterinariasjb.model.Usuario;
import pe.veterinariasjb.repository.UsuarioRepository;

@Controller
@AllArgsConstructor
public class LoginController {

	private UsuarioRepository repoUsu;

	@GetMapping("/login")
	public String cargarLogin(Model model) {
		return "login";
	}

	@PostMapping("/login")
	public String login(@RequestParam("txtUsuario") String usuario, @RequestParam("txtClave") String clave,
			Model model) {
		Usuario usu = repoUsu.findByUsuarioAndClave(usuario, clave);
		if (usu != null) {
			model.addAttribute("Mensaje", "Bienvenido " + usu.getNombre_usuario());
			return "index";
		} else {
			model.addAttribute("mensaje", "El usuario o la contraseña son incorrectos");
			model.addAttribute("cssmensaje", "alert alert-warning m-3");
			return "login";
		}

	}

}
