package co.com.api.rest.usuario;

import co.com.api.helper.util.PasswordValidatorUtil;
import co.com.api.rest.usuario.requests.RegisterRequest;
import co.com.api.model.common.exception.ErrorException;
import co.com.api.model.usuario.Usuario;
import co.com.api.usecase.RegistroUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class UsuarioControllerTest {

    @Mock
    private RegistroUseCase registroUseCase;

    @Mock
    private PasswordValidatorUtil passwordValidatorUtil;

    @InjectMocks
    private UsuarioController usuarioController;

    @BeforeEach
    void init() {
        openMocks(this);
    }

    @Test
    void getUserExitoso() throws ErrorException {
        when(registroUseCase.findById(Mockito.anyString())).thenReturn(
                Usuario.builder()
                        .usuarioId("1")
                        .build());

        var result = usuarioController.getUser("1");

        assertThat(result).isNotNull();
        Assertions.assertEquals("1", Objects.requireNonNull(result.getUsuarioId()));
    }

    @Test
    void createUserExitoso() throws ErrorException {

        when(passwordValidatorUtil.isValid(anyString())).thenReturn(true);
        when(registroUseCase.save(Mockito.any())).thenReturn(
                Usuario.builder()
                        .usuarioId("1")
                        .created(LocalDateTime.now())
                        .modified(LocalDateTime.now())
                        .lastLogin(LocalDateTime.now())
                        .token("1")
                        .isactive(true)
                        .build());

        RegisterRequest request = RegisterRequest.builder()
                .email("email")
                .password("password")
                .nombre("nombre")
                .telefonos(new ArrayList<>())
                .build();

        var result = usuarioController.registerUser(request);

        assertThat(result).isNotNull();
        Assertions.assertEquals("1", Objects.requireNonNull(result.getBody()).getId());
    }

    @Test
    void getUserError() throws ErrorException {
        when(registroUseCase.findById(Mockito.anyString())).thenThrow(new NullPointerException());

        Assertions.assertThrows(NullPointerException.class, () ->
                usuarioController.getUser("1"));
    }

    @Test
    void createUserError() throws ErrorException {

        when(passwordValidatorUtil.isValid(anyString())).thenReturn(true);
        when(registroUseCase.save(Mockito.any())).thenThrow(new ErrorException("",400));

        Assertions.assertThrows(ErrorException.class, () ->
                usuarioController.registerUser(RegisterRequest.builder()
                        .email("user@email.com")
                                .nombre("String")
                                .password("String")
                                .telefonos(new ArrayList<>())
                        .build()));
    }


}