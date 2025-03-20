package co.com.api.usecase;

import co.com.api.model.telefono.Telefono;
import co.com.api.model.telefono.gateways.TelefonoRepository;
import co.com.api.model.usuario.Usuario;
import co.com.api.model.usuario.gateways.UsuarioRepository;
import co.com.api.model.common.exception.ErrorException;
import co.com.api.model.common.exception.codigoError;
import co.com.api.model.common.jwt.JWTGateway;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;


@RequiredArgsConstructor
public class RegistroUseCase {

    private final UsuarioRepository usuarioRepository;

    private final TelefonoRepository telefonoRepository;

    private final JWTGateway jwtGateway;

    public Usuario save(Usuario usuario) throws ErrorException {
        boolean existingUser = this.usuarioRepository.existByEmail(usuario.getEmail());
        if (existingUser) {
            throw new ErrorException("El usuario ya esta regiestrado", codigoError.FOUND.getCode());
        }

        usuario.setCreated(LocalDateTime.now());
        usuario.setModified(LocalDateTime.now());
        usuario.setLastLogin(LocalDateTime.now());
        usuario.setIsactive(true);
        usuario.setPassword(jwtGateway.encritarPassword(usuario.getPassword()));
        Usuario usuarioResult = this.usuarioRepository.save(usuario);

        telefonoRepository.saveAll(usuario.getPhones().stream()
                .map(phoneRequest -> Telefono.builder()
                        .contrycode(phoneRequest.getContrycode())
                        .number(phoneRequest.getNumber())
                        .citycode(phoneRequest.getCitycode())
                        .userId(usuarioResult.getUserId())
                        .build())
                .toList());

        HashMap<String,Object> map = new HashMap<>();
        map.put("email", usuario.getEmail());
        map.put("rol", List.of("CLIENT"));
        usuarioResult.setToken(jwtGateway.generateToken(usuario.getEmail(),map));

        return this.usuarioRepository.save(usuarioResult);
    }

    public Usuario findById(String id) throws ErrorException {
        Usuario usuario = this.usuarioRepository.findById(id);

        if (usuario == null)
            throw new ErrorException("El usuario no existe", codigoError.NOT_FOUND.getCode());

        return usuario;
    }

    public Usuario updateUser(Usuario usuario) {
        boolean existingUser = this.usuarioRepository.existById(String.valueOf(usuario.getUserId()));
        return this.usuarioRepository.update(usuario);
    }
}
