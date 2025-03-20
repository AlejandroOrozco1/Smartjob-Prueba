package co.com.api.rest.usuario.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Builder
public record RegisterRequest (
        @NotBlank(message = "El nombre no puede estar vacío")
        String nombre,

        @Email
        @NotBlank(message = "El email no puede estar vacío")
        String email,

        @NotBlank(message = "La contraseña no puede estar vacía")
        @Length(max = 20, min = 8, message = "La contraseña debe tener entre 8 y 20 caracteres")
        String password,

        @NotEmpty(message = "El teléfono no puede estar vacío")
        List<TelefonoResquet> telefonos
) {}
