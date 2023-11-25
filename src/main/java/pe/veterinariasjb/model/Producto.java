package pe.veterinariasjb.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name = "productos")
@Data
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private int idProducto;

    @NotBlank(message = "Ingrese un nombre")
    @Column(name = "nombre_producto")
    private String nombreProducto;

    @Column(name = "descripcion_producto")
    private String descripcionProducto;

    @NotNull(message = "Ingrese un precio")
    @Digits(integer = 3, fraction = 2)
    @Column(name = "precio_producto")
    private BigDecimal precioProducto;

    @Min(0)
    @NotNull(message = "Ingrese una cantidad")
    private int stock;

    @NotNull
    @Column(name = "estado_producto")
    private Boolean estadoProducto;
}
