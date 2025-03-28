package co.com.api.rest.usuario.requests;

import co.com.api.model.telefono.Telefono;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
public class UpdateUsuarioRequest {
    @NotEmpty(message = "El nombre no puede estar vacio")
    private String nombre;
    @Email
    @NotEmpty(message = "El email no puede estar vacio")
    private String email;
    @NotEmpty(message = "El telefono no puede estar vacio")
    private List<Telefono> telefonos;
}
