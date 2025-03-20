package co.com.api.jpa.user;

import co.com.api.jpa.user.entities.UsuarioEntity;
import co.com.api.model.usuario.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.reactivecommons.utils.ObjectMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class UsuarioRepositoryAdapterTest {


    @Mock
    private UsuarioDataRepository repository;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private UsuarioRepositoryAdapter adapter;

    private Usuario usuario;

    private UsuarioEntity usuarioEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        when(objectMapper.map("value", Object.class)).thenReturn("value");

        adapter = new UsuarioRepositoryAdapter(repository, objectMapper);

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

       usuarioEntity = UsuarioEntity.builder()
               .email("xxx@xxx.com")
               .nombre("")
               .usuarioId("")
               .telefonos(new ArrayList<>())
               .lastLogin(LocalDateTime.now())
               .created(LocalDateTime.now())
               .modified(LocalDateTime.now())
               .isactive(true)
               .build();
    }

    @Test
    void testSave() {

        when(repository.save(Mockito.any())).thenReturn(usuarioEntity);
        when(objectMapper.map(usuarioEntity, Usuario.class)).thenReturn(usuario);

        Usuario result = adapter.save(usuario);

        assertEquals(usuario, result);
    }

    @Test
    void testFindById() {
        when(repository.findById("id")).thenReturn(Optional.of(usuarioEntity));
        when(objectMapper.map(usuarioEntity, Usuario.class)).thenReturn(usuario);

        Usuario result = adapter.findById("id");

        assertEquals(usuario, result);
    }
}
