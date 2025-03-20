package co.com.api.jpa.telefono;

import co.com.api.jpa.telefono.entities.TelefonoEntity;
import co.com.api.model.telefono.Telefono;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.reactivecommons.utils.ObjectMapper;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class TelefonoRepositoryAdapterTest {


    @Mock
    private TelefonoDataRepository repository;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private TelefonoRepositoryAdapter adapter;

    private Telefono telefono;

    private TelefonoEntity telefonoEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        when(objectMapper.map("value", Object.class)).thenReturn("value");

        adapter = new TelefonoRepositoryAdapter(repository, objectMapper);

        telefono = Telefono.builder()
                .userId("1")
                .phoneId("1")
                .number("9")
                .citycode("8")
                .citycode("7")
                .build();

       telefonoEntity = TelefonoEntity.builder()
               .usuarioId("1")
               .telefonoId("1")
               .numero("9")
               .codigoCiudad("8")
               .codigoCiudad("7")
               .build();
    }

    @Test
    void testSave() {

        when(repository.save(Mockito.any())).thenReturn(telefonoEntity);
        when(objectMapper.map(telefonoEntity, Telefono.class)).thenReturn(telefono);

        Telefono result = adapter.save(telefono);

        assertEquals(telefono, result);
    }

    @Test
    void testSaveAll() {

        when(repository.save(Mockito.any())).thenReturn(telefonoEntity);
        when(objectMapper.map(telefonoEntity, Telefono.class)).thenReturn(telefono);

        List<Telefono> result = adapter.saveAll(List.of(telefono));

        assertEquals(List.of(telefono), result);
    }

    @Test
    void testFindById() {
        when(repository.findById("id")).thenReturn(Optional.of(telefonoEntity));
        when(objectMapper.map(telefonoEntity, Telefono.class)).thenReturn(telefono);

        Telefono result = adapter.findById("id");

        assertEquals(telefono, result);
    }
}
