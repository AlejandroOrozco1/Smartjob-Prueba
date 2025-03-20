package co.com.api.jpa.telefono.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "telefono")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TelefonoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String telefonoId;
    private String numero;
    private String codigoCiudad;
    private String codigoPais;
    private String usuarioId;
}
