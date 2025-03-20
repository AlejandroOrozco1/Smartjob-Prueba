package co.com.api.helper.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PasswordValidatorUtil {

    @Value("${config.password-regex}")
    private String passwordRegex;

    public boolean isValid(String password) {
        return password != null && password.matches(passwordRegex);
    }
}
