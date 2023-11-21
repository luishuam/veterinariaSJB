package pe.veterinariasjb.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name = "productos")
@Data
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_producto;
    @NotNull
    private String nombre_producto;
    private String descripcion_producto;
    @Digits(integer = 3, fraction = 2)
    private BigDecimal precio_producto;
    @Min(value = 0)
    private int stock;
    @NotNull
    private Boolean estado_producto;
}
