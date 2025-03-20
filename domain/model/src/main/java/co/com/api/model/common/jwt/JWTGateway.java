package co.com.api.model.common.jwt;

import java.util.Map;

public interface JWTGateway {
    String generateToken(String user, Map<String, Object> claims);
    String encritarPassword(String password);
}
