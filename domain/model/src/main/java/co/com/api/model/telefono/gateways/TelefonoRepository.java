package co.com.api.model.telefono.gateways;

import co.com.api.model.telefono.Telefono;

import java.util.List;

public interface TelefonoRepository {
    Telefono save(Telefono telefono);

    List<Telefono> saveAll(List<Telefono> telefonos);

    Telefono findById(String id);

    Telefono update(Telefono telefono);

    boolean existById(String id);
}
