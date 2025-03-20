package co.com.api.jpa.user;

import co.com.api.jpa.user.entities.UserEntity;
import co.com.api.jpa.helper.AdapterOperations;
import co.com.api.model.usuario.Usuario;
import co.com.api.model.usuario.gateways.UsuarioRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioRepositoryAdapter extends AdapterOperations<Usuario, UserEntity, String, UserDataRepository>
 implements UsuarioRepository
{

    public UsuarioRepositoryAdapter(UserDataRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, Usuario.class));
    }

    @Override
    public Usuario update(Usuario usuario) {
        return this.save(usuario);
    }

    @Override
    public boolean existById(String id) {
        return this.repository.existsById(id);
    }

    @Override
    public boolean existByEmail(String email) {
        return repository.existsByEmail(email);
    }
}
