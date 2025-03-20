package co.com.api.jpa.phone;

import co.com.api.jpa.helper.AdapterOperations;
import co.com.api.jpa.phone.entities.PhoneEntity;
import co.com.api.model.telefono.Telefono;
import co.com.api.model.telefono.gateways.TelefonoRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PhoneRepositoryAdapter extends AdapterOperations<Telefono, PhoneEntity, String, PhoneDataRepository>
 implements TelefonoRepository
{

    public PhoneRepositoryAdapter(PhoneDataRepository repository, ObjectMapper mapper) {
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
