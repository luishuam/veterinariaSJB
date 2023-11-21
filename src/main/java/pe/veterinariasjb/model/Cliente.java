package pe.veterinariasjb.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(name = "clientes")
@Data
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_cliente;
    @NotNull
    private String nombres_cliente;
    @Size(min = 9, max = 9)
    private String telefono_cliente;
    @NotNull
    private String direccion_cliente;
}
