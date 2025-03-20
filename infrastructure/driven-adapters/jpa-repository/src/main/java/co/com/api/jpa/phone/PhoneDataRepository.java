package co.com.api.jpa.phone;

import co.com.api.jpa.phone.entities.PhoneEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface PhoneDataRepository extends CrudRepository<PhoneEntity, String>, QueryByExampleExecutor<PhoneEntity> {
}
