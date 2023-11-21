package pe.veterinariasjb.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Table(name = "citas")
@Data
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_cita;
    @FutureOrPresent
    private LocalDateTime fecha_cita;
    @NotBlank
    private String descripcion_cita;
    private int mascotas_id;
    private int clientes_id;
    private int servicios_tipo;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id_mascota", insertable = true, updatable = true)
    public Mascota objMascota;
    @ManyToOne
    @JoinColumn(referencedColumnName = "id_cliente", insertable = true, updatable = true)
    public Cliente objCliente2;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id_servicio", insertable = true, updatable = true)
    public Servicio objServicio;

}
