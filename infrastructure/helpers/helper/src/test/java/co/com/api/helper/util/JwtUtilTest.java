package co.com.api.helper.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.MalformedJwtException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class JwtUtilTest {

    private static final String SECRET_KEY = "secretsecretsecretsecretsecretsecretsecretsecret";
    private static final String USERNAME = "testUser";
    private static final long EXPIRATION_TIME = 10L;

    @InjectMocks
    private JwtUtil jwtUtil;

    @BeforeEach
    void setUp() {
        jwtUtil = new JwtUtil(SECRET_KEY);
        ReflectionTestUtils.setField(jwtUtil, "expirationTime", EXPIRATION_TIME);
    }

    @Test
    void shouldGenerateValidToken() {
        String token = jwtUtil.generateToken(USERNAME, Collections.emptyMap());
        assertNotNull(token);
    }

    @Test
    void shouldParseValidToken() {
        String token = jwtUtil.generateToken(USERNAME, Collections.emptyMap());
        Jws<Claims> claimsJws = jwtUtil.parseJwt(token);
        assertEquals(USERNAME, claimsJws.getBody().getSubject());
    }

    @Test
    void shouldThrowExceptionForInvalidToken() {
        String invalidToken = "invalid.token.value";
        assertThrows(MalformedJwtException.class, () -> jwtUtil.parseJwt(invalidToken));
    }

    @Test
    void shouldEncryptPassword() {
        String password = "securePassword";
        String encryptedPassword = jwtUtil.encritarPassword(password);
        assertNotNull(encryptedPassword);
        assertNotEquals(password, encryptedPassword);
    }

    @Test
    void shouldMatchEncryptedPassword() {
        String password = "securePassword";
        String encryptedPassword = jwtUtil.encritarPassword(password);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        assertTrue(encoder.matches(password, encryptedPassword));
    }
}
