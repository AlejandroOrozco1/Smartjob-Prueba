package co.com.api.rest.user;

import co.com.api.rest.user.response.InfoRegister;
import co.com.api.rest.user.requests.RegisterRequest;
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
@RequestMapping(value = "/api/user", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class UserController {

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
                .name(request.name())
                .password(request.password())
                .email(request.email())
                .phones(request.phones().stream()
                        .map(phoneRequest -> Telefono.builder()
                                .contrycode(phoneRequest.getContrycode())
                                .number(phoneRequest.getNumber())
                                .citycode(phoneRequest.getCitycode())
                                .build())
                        .toList())
                .build();


        Usuario savedUsuario = registroUseCase.save(usuario);
        return ResponseEntity.ok(InfoRegister.builder()
                        .id(savedUsuario.getUserId())
                        .created(savedUsuario.getCreated())
                        .modified(savedUsuario.getModified())
                        .last_login(savedUsuario.getLastLogin())
                        .token(savedUsuario.getToken())
                        .isactive(savedUsuario.isIsactive())
                .build());
    }
}
