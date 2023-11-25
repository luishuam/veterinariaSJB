package pe.veterinariasjb.model;

import java.time.LocalDate;

import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;

@Entity
@Table(name = "citas")
@Data
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cita")
    private int idCita;

    @Column(name = "fecha_cita")
    @NotNull
    private LocalDate fecha;

    @Column(name = "hora_cita")
    @NotNull(message = "Colocar hora")
    private LocalTime hora;

    @NotBlank(message = "Colocar descripcion")
    @Column(name = "descripcion_cita")
    private String descripcion;

    // @Column(name = "mascotas_id")
    // private int mascotasId;

    // @Column(name = "clientes_id")
    // private int clientesId;

    // @Column(name = "servicios_tipo")
    // private int serviciosTipo;

    @ManyToOne
    @JoinColumn(name = "mascotas_id", insertable = true, updatable = true)
    public Mascota idMascota;

    @ManyToOne
    @JoinColumn(name = "clientes_id", insertable = true, updatable = true)
    public Cliente idCliente;

    @ManyToOne
    @JoinColumn(name = "servicios_tipo", insertable = true, updatable = true)
    public Servicio idServicio;

    @NotNull
    @Column(name = "estado_cita")
    private Boolean estadoCita;

}
