package co.com.api.usecase;

import co.com.api.model.common.exception.ErrorException;
import co.com.api.model.common.jwt.JWTGateway;
import co.com.api.model.telefono.Telefono;
import co.com.api.model.telefono.gateways.TelefonoRepository;
import co.com.api.model.usuario.Usuario;
import co.com.api.model.usuario.gateways.UsuarioRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.Mockito.when;

class RegistroUseCaseTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private TelefonoRepository telefonoRepository;

    @Mock
    private JWTGateway jwtGateway;

    @InjectMocks
    private RegistroUseCase registroUseCase;

    private Usuario usuario;

    private Telefono telefono;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        usuario = Usuario.builder()
                .email("xxx@xxx.com")
                .name("")
                .userId("")
                .phones(new ArrayList<>())
                .lastLogin(LocalDateTime.now())
                .created(LocalDateTime.now())
                .modified(LocalDateTime.now())
                .isactive(true)
                .build();

        telefono = Telefono.builder()
                .userId("1")
                .phoneId("1")
                .number("9")
                .citycode("8")
                .citycode("7")
                .build();

    }

    @Test
    void createUserExitoso() throws ErrorException {

        when(telefonoRepository.saveAll(Mockito.any())).thenReturn(List.of(telefono));
        when(usuarioRepository.save(Mockito.any())).thenReturn(usuario);

        Usuario result = registroUseCase.save(usuario);

        assertThat(result).isNotNull();
        Assertions.assertEquals(usuario.getUserId(), Objects.requireNonNull(result.getUserId()));
    }

    @Test
    void getUserError() {
        when(usuarioRepository.findById(Mockito.anyString())).thenThrow(new NullPointerException());

        Assertions.assertThrows(NullPointerException.class, () ->
                registroUseCase.findById("1"));
    }

    @Test
    void createUserError(){

        when(usuarioRepository.existByEmail(Mockito.any())).thenReturn(true);

        Assertions.assertThrows(ErrorException.class, () ->
                registroUseCase.save(usuario));
    }

}