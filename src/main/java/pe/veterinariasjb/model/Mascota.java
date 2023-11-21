package pe.veterinariasjb.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(name = "mascotas")
@Data
public class Mascota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_mascota;
    @NotNull
    private String nombre_mascota;
    @NotNull
    private String especie;
    @NotNull
    private String raza;
    @Size(min = 0)
    private Integer edad;
    @Digits(integer = 3, fraction = 2)
    private BigDecimal peso;
    private int clientes_id;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id_cliente", insertable = true, updatable = true)
    public Cliente objCliente;
}
