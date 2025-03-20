package co.com.api.jpa.user;

import co.com.api.jpa.user.entities.UsuarioEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface UsuarioDataRepository extends CrudRepository<UsuarioEntity, String>, QueryByExampleExecutor<UsuarioEntity> {
    Boolean existsByEmail(String email);
}
