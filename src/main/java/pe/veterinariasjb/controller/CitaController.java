package pe.veterinariasjb.controller;

import java.io.OutputStream;
import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
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

        System.out.println(cita.getIdCita());

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

    private DataSource dataSource; // javax.sql

    private ResourceLoader resourceLoader; // core.io
    // reportes filtrados por codigo

    @GetMapping("/reporte/{idCita}")
    public void reportesConFiltro(@PathVariable int idCita, HttpServletResponse response, @ModelAttribute Cita cita) {
        // opcion para descargar directamente el PDF
        // response.setHeader("Content-Disposition", "attachment;
        // filename=\"reporte.pdf\";");
        // Opcion para visaulizar el PDF
        response.setHeader("Content-Disposition", "inline;");
        //
        response.setContentType("application/pdf");
        try {
            // Definir los parametros
            System.out.println("llega " + cita);

            HashMap<String, Object> parametros = new HashMap<>();
            parametros.put("codigo", cita.getIdCita());

            // Carga el jasper
            String ru = resourceLoader.getResource("classpath:reports/boleta.jasper").getURI().getPath();
            // Combinar el .jasper con la conexion
            JasperPrint jasperPrint = JasperFillManager.fillReport(ru, parametros, dataSource.getConnection());
            OutputStream outStream = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
// Fin
