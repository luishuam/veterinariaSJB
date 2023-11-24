package pe.veterinariasjb.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "trabajadores")
@Data
public class Trabajador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_trabajador")
    private int idTrabajador;

    @Column(name = "nombre_trabajador")
    private String nombreTrabajador;

    // @Column(name = "cargos_id")
    // private int cargosId;

    // @Column(name = "turnos_id")
    // private int turnosId;

    @ManyToOne
    @JoinColumn(name = "cargos_id", insertable = false, updatable = false)
    public Cargo idCargo;

    @ManyToOne
    @JoinColumn(name = "turnos_id", insertable = false, updatable = false)
    public Turno idTurno;

}
