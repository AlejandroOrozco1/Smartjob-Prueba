package co.com.api.rest.user;

import co.com.api.helper.util.PasswordValidatorUtil;
import co.com.api.rest.user.requests.RegisterRequest;
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
    private UserController userController;

    @BeforeEach
    void init() {
        openMocks(this);
    }

    @Test
    void getUserExitoso() throws ErrorException {
        when(registroUseCase.findById(Mockito.anyString())).thenReturn(
                Usuario.builder()
                        .userId("1")
                        .build());

        var result = userController.getUser("1");

        assertThat(result).isNotNull();
        Assertions.assertEquals("1", Objects.requireNonNull(result.getUserId()));
    }

    @Test
    void createUserExitoso() throws ErrorException {

        when(passwordValidatorUtil.isValid(anyString())).thenReturn(true);
        when(registroUseCase.save(Mockito.any())).thenReturn(
                Usuario.builder()
                        .userId("1")
                        .created(LocalDateTime.now())
                        .modified(LocalDateTime.now())
                        .lastLogin(LocalDateTime.now())
                        .token("1")
                        .isactive(true)
                        .build());

        RegisterRequest request = RegisterRequest.builder()
                .email("email")
                .password("password")
                .name("nombre")
                .phones(new ArrayList<>())
                .build();

        var result = userController.registerUser(request);

        assertThat(result).isNotNull();
        Assertions.assertEquals("1", Objects.requireNonNull(result.getBody()).getId());
    }

    @Test
    void getUserError() throws ErrorException {
        when(registroUseCase.findById(Mockito.anyString())).thenThrow(new NullPointerException());

        Assertions.assertThrows(NullPointerException.class, () ->
                userController.getUser("1"));
    }

    @Test
    void createUserError() throws ErrorException {

        when(passwordValidatorUtil.isValid(anyString())).thenReturn(true);
        when(registroUseCase.save(Mockito.any())).thenThrow(new ErrorException("",400));

        Assertions.assertThrows(ErrorException.class, () ->
                userController.registerUser(RegisterRequest.builder()
                        .email("xxx@xxx.com")
                                .name("String")
                                .password("Strin")
                                .phones(new ArrayList<>())
                        .build()));
    }


}