package co.com.api.rest.usuario;

import co.com.api.rest.usuario.response.InfoRegister;
import co.com.api.rest.usuario.requests.RegisterRequest;
import co.com.api.helper.util.PasswordValidatorUtil;
import co.com.api.model.common.exception.ErrorException;
import co.com.api.model.telefono.Telefono;
import co.com.api.model.usuario.Usuario;
import co.com.api.usecase.RegistroUseCase;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/usuario", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class UsuarioController {

    private final RegistroUseCase registroUseCase;
    private final PasswordValidatorUtil passwordValidatorUtil;

    @GetMapping(path = "/{id}")
    @SecurityRequirement(name = "bearerAuth")
    public Usuario getUser(@PathVariable("id") String id) throws ErrorException {
        return registroUseCase.findById(id);
    }

    @PostMapping(path = "/register")
    public ResponseEntity<InfoRegister> registerUser(@Valid @RequestBody RegisterRequest request) throws ErrorException {

        if (!passwordValidatorUtil.isValid(request.password())) {
            throw new ErrorException("La contraseña no cumple con los requisitos de seguridad.", HttpStatus.BAD_REQUEST.value());
        }

        Usuario usuario = Usuario.builder()
                .nombre(request.nombre())
                .password(request.password())
                .email(request.email())
                .telefonos(request.telefonos().stream()
                        .map(phoneRequest -> Telefono.builder()
                                .contrycode(phoneRequest.getCoidgoPais())
                                .number(phoneRequest.getNumero())
                                .citycode(phoneRequest.getCodigoCiudad())
                                .build())
                        .toList())
                .build();


        Usuario savedUsuario = registroUseCase.save(usuario);
        return ResponseEntity.ok(InfoRegister.builder()
                        .id(savedUsuario.getUsuarioId())
                        .created(savedUsuario.getCreated())
                        .modified(savedUsuario.getModified())
                        .last_login(savedUsuario.getLastLogin())
                        .token(savedUsuario.getToken())
                        .isactive(savedUsuario.isIsactive())
                .build());
    }
}
