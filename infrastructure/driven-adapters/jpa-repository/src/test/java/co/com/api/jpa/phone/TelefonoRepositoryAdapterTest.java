package co.com.api.jpa.phone;

import co.com.api.jpa.phone.entities.PhoneEntity;
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
    private PhoneDataRepository repository;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private PhoneRepositoryAdapter adapter;

    private Telefono telefono;

    private PhoneEntity phoneEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        when(objectMapper.map("value", Object.class)).thenReturn("value");

        adapter = new PhoneRepositoryAdapter(repository, objectMapper);

        telefono = Telefono.builder()
                .userId("1")
                .phoneId("1")
                .number("9")
                .citycode("8")
                .citycode("7")
                .build();

       phoneEntity = PhoneEntity.builder()
               .userId("1")
               .phoneId("1")
               .number("9")
               .citycode("8")
               .citycode("7")
               .build();
    }

    @Test
    void testSave() {

        when(repository.save(Mockito.any())).thenReturn(phoneEntity);
        when(objectMapper.map(phoneEntity, Telefono.class)).thenReturn(telefono);

        Telefono result = adapter.save(telefono);

        assertEquals(telefono, result);
    }

    @Test
    void testSaveAll() {

        when(repository.save(Mockito.any())).thenReturn(phoneEntity);
        when(objectMapper.map(phoneEntity, Telefono.class)).thenReturn(telefono);

        List<Telefono> result = adapter.saveAll(List.of(telefono));

        assertEquals(List.of(telefono), result);
    }

    @Test
    void testFindById() {
        when(repository.findById("id")).thenReturn(Optional.of(phoneEntity));
        when(objectMapper.map(phoneEntity, Telefono.class)).thenReturn(telefono);

        Telefono result = adapter.findById("id");

        assertEquals(telefono, result);
    }
}
