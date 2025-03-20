package co.com.api.jpa.telefono;

import co.com.api.jpa.telefono.entities.TelefonoEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface TelefonoDataRepository extends CrudRepository<TelefonoEntity, String>, QueryByExampleExecutor<TelefonoEntity> {
}
