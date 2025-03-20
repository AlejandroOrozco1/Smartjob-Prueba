package co.com.api.model.usuario;

import co.com.api.model.telefono.Telefono;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Usuario {
    private String usuarioId;
    private String nombre;
    private String email;
    private String password;
    private String token;
    private List<Telefono> telefonos;
    private LocalDateTime created;
    private LocalDateTime modified;
    private LocalDateTime lastLogin;
    private boolean isactive;

}
