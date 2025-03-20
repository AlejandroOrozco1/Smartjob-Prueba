package co.com.api.jpa.telefono;

import co.com.api.jpa.helper.AdapterOperations;
import co.com.api.jpa.telefono.entities.TelefonoEntity;
import co.com.api.model.telefono.Telefono;
import co.com.api.model.telefono.gateways.TelefonoRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TelefonoRepositoryAdapter extends AdapterOperations<Telefono, TelefonoEntity, String, TelefonoDataRepository>
 implements TelefonoRepository
{

    public TelefonoRepositoryAdapter(TelefonoDataRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, Telefono.class));
    }

    @Override
    public List<Telefono> saveAll(List<Telefono> telefonos) {
        return this.saveAllEntities(telefonos);
    }

    @Override
    public Telefono update(Telefono user) {
        return this.save(user);
    }

    @Override
    public boolean existById(String id) {
        return this.repository.existsById(id);
    }

}
