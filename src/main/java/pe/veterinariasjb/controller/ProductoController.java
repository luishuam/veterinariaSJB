package pe.veterinariasjb.controller;

import java.io.OutputStream;

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
import pe.veterinariasjb.model.Producto;
import pe.veterinariasjb.repository.ProductoRepository;

@Controller
@RequestMapping("productos")
@AllArgsConstructor
public class ProductoController {

    ProductoRepository productoRepository;

    @GetMapping
    public String cargarListadoProductos(Model model) {

        model.addAttribute("lstProductos", productoRepository.findAll());

        model.addAttribute("producto", new Producto());

        return "productos";
    }

    @GetMapping("/editar/{idProducto}")
    public String editarProducto(@PathVariable int idProducto, Model model) {

        Producto producto = productoRepository.findById(idProducto).get();

        System.out.println(producto);

        model.addAttribute("producto", producto);

        model.addAttribute("lstProductos", productoRepository.findAll());

        return "productos";
    }

    @PostMapping("/guardar")
    public String guardarProducto(@Valid @ModelAttribute Producto producto, Model model) {
        try {
            productoRepository.save(producto);
            model.addAttribute("mensaje", "Producto Guardado Correctamente");
            model.addAttribute("cssmensaje", "alert alert-success");
            return "redirect:/productos";
        } catch (Exception e) {
            System.out.println(e.getCause().getMessage());
            System.out.println(producto);
            model.addAttribute("mensaje", "Error en el Guardado de Informacion");
            model.addAttribute("cssmensaje", "alert alert-danger");
            return "redirect:/productos";
        }

    }

    // HardDelete
    @GetMapping("/eliminar/{idProducto}")
    public String eliminarCita(@PathVariable int idProducto, Model model) {

        try {
            Producto p = productoRepository.findById(idProducto).get();
            productoRepository.delete(p);

            model.addAttribute("producto", new Producto());
            model.addAttribute("lstProductos", productoRepository.findAll());

            return "redirect:/productos";
        } catch (Exception e) {
            return "redirect:/productos";
        }
    }

    // reporte
    private DataSource dataSource; // javax.sql

    private ResourceLoader resourceLoader; // core.io

    @GetMapping("/reporte")
    public void generarReporte(HttpServletResponse response) {
        response.setHeader("Content-Disposition", "inline;");
        //
        response.setContentType("application/pdf");
        try {
            // Carga el jasper
            String ru = resourceLoader.getResource("classpath:reports/productoreporte.jasper").getURI().getPath();
            // Combinar el .jasper con la conexion
            JasperPrint jasperPrint = JasperFillManager.fillReport(ru, null, dataSource.getConnection());
            OutputStream outStream = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
        } catch (Exception e) {
            System.out.println("error en el pdf");
            e.printStackTrace();
        }
    }
}
