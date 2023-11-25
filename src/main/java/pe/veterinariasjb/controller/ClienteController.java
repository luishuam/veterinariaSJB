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
import pe.veterinariasjb.model.Cliente;
import pe.veterinariasjb.repository.ClienteRepository;

@Controller
@RequestMapping("/pacientes/clientes")
@AllArgsConstructor
public class ClienteController {

    ClienteRepository clienteRepository;

    @GetMapping
    public String cargarListadoClientes(Model model) {

        model.addAttribute("lstClientes", clienteRepository.findAll());

        model.addAttribute("cliente", new Cliente());

        return "duenos";
    }

    @GetMapping("/editar/{idCliente}")
    public String editarCliente(@PathVariable int idCliente, Model model) {

        Cliente cliente = clienteRepository.findById(idCliente).get();

        model.addAttribute("cliente", cliente);

        model.addAttribute("lstClientes", clienteRepository.findAll());

        return "duenos";
    }

    @PostMapping("/guardar")
    public String guardarCliente(@Valid @ModelAttribute Cliente cliente, Model model) {
        try {
            System.out.println(cliente);
            clienteRepository.save(cliente);
            model.addAttribute("mensaje", "Informacion Guardada Correctamente");
            model.addAttribute("cssmensaje", "alert alert-success");
            return "redirect:/pacientes/clientes";
        } catch (Exception e) {
            System.out.println(e.getCause().getMessage());
            System.out.println(cliente);
            model.addAttribute("mensaje", "Error en el Guardado de Informacion");
            model.addAttribute("cssmensaje", "alert alert-danger");
            return "redirect:/pacientes/clientes";
        }

    }

    // HardDelete
    @GetMapping("/eliminar/{idCliente}")
    public String eliminarCliente(@PathVariable int idCliente, Model model) {

        try {
            Cliente c = clienteRepository.findById(idCliente).get();
            clienteRepository.delete(c);

            model.addAttribute("cliente", new Cliente());
            // model.addAttribute("lstMascotas", mascotaRepository.findAll());
            // model.addAttribute("lstClientes", clienteRepository.findAll());

            return "redirect:/pacientes/clientes";
        } catch (Exception e) {
            return "redirect:/pacientes/clientes";
        }
    }
}
