package pe.veterinariasjb.model;

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
    private int id_trabajador;
    private String nombre_trabajador;
    private int cargos_id;
    private int turnos_id;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id_cargo", insertable = false, updatable = false)
    public Cargo objCargo;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id_turno", insertable = false, updatable = false)
    public Turno objTurno;

}
