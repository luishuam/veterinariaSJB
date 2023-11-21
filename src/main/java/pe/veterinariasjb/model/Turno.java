package pe.veterinariasjb.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "turnos")
@Data
public class Turno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_turno;
    private String nombre_turno;
}
