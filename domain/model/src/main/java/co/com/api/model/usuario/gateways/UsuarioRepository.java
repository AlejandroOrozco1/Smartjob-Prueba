package co.com.api.model.usuario.gateways;

import co.com.api.model.usuario.Usuario;

public interface UsuarioRepository {
    Usuario save(Usuario usuario);

    Usuario findById(String id);

    Usuario update(Usuario usuario);

    boolean existById(String id);

    boolean existByEmail(String email);
}
