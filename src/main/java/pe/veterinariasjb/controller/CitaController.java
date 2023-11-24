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
import pe.veterinariasjb.model.Cita;
import pe.veterinariasjb.repository.CitaRepository;
import pe.veterinariasjb.repository.ClienteRepository;
import pe.veterinariasjb.repository.MascotaRepository;

import pe.veterinariasjb.repository.ServicioRepository;

@Controller
@RequestMapping("citas")
@AllArgsConstructor
public class CitaController {

    CitaRepository citaRepository;

    MascotaRepository mascotaRepository;

    ClienteRepository clienteRepository;

    ServicioRepository servicioRepository;

    @GetMapping
    public String cargarListadoCita(Model model) {

        model.addAttribute("lstMascotas", mascotaRepository.findAll());
        model.addAttribute("lstClientes", clienteRepository.findAll());
        model.addAttribute("lstServicios", servicioRepository.findAll());

        model.addAttribute("lstCitas", citaRepository.findAll());

        model.addAttribute("cita", new Cita());

        return "citas";
    }

    @GetMapping("/editar/{idCita}")
    public String editarCita(@PathVariable int idCita, Model model) {

        Cita cita = citaRepository.findById(idCita).get();

        System.out.println(cita);

        model.addAttribute("cita", cita);

        model.addAttribute("lstCitas", citaRepository.findAll());

        model.addAttribute("lstMascotas", mascotaRepository.findAll());
        model.addAttribute("lstClientes", clienteRepository.findAll());
        model.addAttribute("lstServicios", servicioRepository.findAll());

        return "citas";
    }

    @PostMapping("/guardar")
    public String guardarCita(@Valid @ModelAttribute Cita cita, Model model) {
        try {
            citaRepository.save(cita);
            model.addAttribute("mensaje", "Informacion Guardada Correctamente");
            model.addAttribute("cssmensaje", "alert alert-success");
            return "redirect:/citas";
        } catch (Exception e) {
            System.out.println(e.getCause().getMessage());
            System.out.println(cita);
            model.addAttribute("mensaje", "Error en el Guardado de Informacion");
            model.addAttribute("cssmensaje", "alert alert-danger");
            return "redirect:/citas";
        }

    }

    // HardDelete
    @GetMapping("/eliminar/{idCita}")
    public String eliminarCita(@PathVariable int idCita, Model model) {

        try {
            Cita c = citaRepository.findById(idCita).get();
            citaRepository.delete(c);

            model.addAttribute("cita", new Cita());
            model.addAttribute("lstMascotas", mascotaRepository.findAll());
            model.addAttribute("lstClientes", clienteRepository.findAll());
            model.addAttribute("lstServicios", servicioRepository.findAll());

            return "redirect:/citas";
        } catch (Exception e) {
            return "redirect:/citas";
        }
    }
}
// Fin
