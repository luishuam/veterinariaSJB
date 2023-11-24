package pe.veterinariasjb.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(name = "clientes")
@Data
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private int idCliente;

    @NotBlank
    @Column(name = "nombres_cliente")
    private String nombresCliente;

    @Size(min = 9, max = 9)
    @NotBlank
    @Column(name = "telefono_cliente")
    private String telefonoCliente;

    @NotBlank
    @Email
    @Column(name = "email_cliente")
    private String email;

    @NotBlank
    @Column(name = "direcccion_cliente")
    private String direccionCliente;

    @NotNull
    @Column(name = "estado_cliente")
    private Boolean estadoCliente;
}
