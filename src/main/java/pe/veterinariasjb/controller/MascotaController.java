package pe.veterinariasjb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import pe.veterinariasjb.model.Mascota;

import pe.veterinariasjb.repository.ClienteRepository;
import pe.veterinariasjb.repository.MascotaRepository;

@Controller
@RequestMapping("/pacientes/mascotas")
@AllArgsConstructor
public class MascotaController {

    MascotaRepository mascotaRepository;

    ClienteRepository clienteRepository;

    @GetMapping
    public String cargarListadoMascotas(Model model) {

        model.addAttribute("lstClientes", clienteRepository.findAll());

        model.addAttribute("lstMascotas", mascotaRepository.findAll());

        model.addAttribute("mascota", new Mascota());

        return "mascotas";
    }

    @GetMapping("/editar/{idMascota}")
    public String editarMascota(@PathVariable int idMascota, Model model) {

        Mascota mascota = mascotaRepository.findById(idMascota).get();

        System.out.println(mascota);

        model.addAttribute("mascota", mascota);
        model.addAttribute("lstMascotas", mascotaRepository.findAll());

        model.addAttribute("lstClientes", clienteRepository.findAll());

        return "mascotas";
    }

    @PostMapping("/guardar")
    public String guardarMascota(@Valid @ModelAttribute Mascota mascota, Model model) {
        try {
            mascotaRepository.save(mascota);
            model.addAttribute("mensaje", "Informacion Guardada Correctamente");
            model.addAttribute("cssmensaje", "alert alert-success");
            return "redirect:/pacientes/mascotas";
        } catch (Exception e) {
            System.out.println(e.getCause().getMessage());
            System.out.println(mascota);
            model.addAttribute("mensaje", "Error en el Guardado de Informacion");
            model.addAttribute("cssmensaje", "alert alert-danger");
            return "redirect:/pacientes/mascotas";
        }

    }

    // HardDelete
    @GetMapping("/eliminar/{idMascota}")
    public String eliminarMascota(@PathVariable int idMascota, Model model) {

        try {
            Mascota m = mascotaRepository.findById(idMascota).get();
            mascotaRepository.delete(m);

            model.addAttribute("mascota", new Mascota());
            // model.addAttribute("lstMascotas", mascotaRepository.findAll());
            // model.addAttribute("lstClientes", clienteRepository.findAll());

            return "redirect:/pacientes/mascotas";
        } catch (Exception e) {
            return "redirect:/pacientes/mascotas";
        }
    }

}
