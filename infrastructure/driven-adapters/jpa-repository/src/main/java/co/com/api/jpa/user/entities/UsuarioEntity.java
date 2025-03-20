package co.com.api.jpa.user.entities;

import co.com.api.jpa.telefono.entities.TelefonoEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String usuarioId;
    private String nombre;
    private String email;
    private String password;
    private String token;
    private LocalDateTime created;
    private LocalDateTime modified;
    private LocalDateTime lastLogin;
    private boolean isactive;
    @OneToMany(mappedBy = "usuarioId")
    private Collection<TelefonoEntity> telefonos;

}
