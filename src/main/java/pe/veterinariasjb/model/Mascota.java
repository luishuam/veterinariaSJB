package pe.veterinariasjb.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Entity
@Table(name = "mascotas")
@Data
public class Mascota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mascota")
    private int idMascota;

    @NotBlank
    @Column(name = "nombre_mascota")
    private String nombreMascota;

    @NotBlank
    private String especie;

    @NotBlank
    private String raza;

    @Min(0)
    @NotNull
    private Integer edad;

    @PositiveOrZero
    @NotNull
    @Digits(integer = 3, fraction = 2)
    private BigDecimal peso;

    @NotNull
    @Column(name = "estado_mascota")
    private Boolean estadoMascota;

    // @Column(name = "clientes_id")
    // private int clientesId;

    @ManyToOne
    @JoinColumn(name = "clientes_id", insertable = true, updatable = true)
    public Cliente idCliente;
}
