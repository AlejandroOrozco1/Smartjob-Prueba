package co.com.api.helper.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;

class PasswordValidatorUtilTest {

    @InjectMocks
    private PasswordValidatorUtil passwordValidatorUtil;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(passwordValidatorUtil, "passwordRegex", "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{8,}$");
    }

    @Test
    void testValidPassword() {
        String validPassword = "Password123";
        assertTrue(passwordValidatorUtil.isValid(validPassword));
    }

    @Test
    void testInvalidPassword_NoUpperCase() {
        String invalidPassword = "password123";
        assertFalse(passwordValidatorUtil.isValid(invalidPassword));
    }

    @Test
    void testInvalidPassword_NoLowerCase() {
        String invalidPassword = "PASSWORD123";
        assertFalse(passwordValidatorUtil.isValid(invalidPassword));
    }

    @Test
    void testInvalidPassword_NoDigits() {
        String invalidPassword = "Password";
        assertFalse(passwordValidatorUtil.isValid(invalidPassword));
    }

    @Test
    void testInvalidPassword_TooShort() {
        String invalidPassword = "P1a";
        assertFalse(passwordValidatorUtil.isValid(invalidPassword));
    }

    @Test
    void testInvalidPassword_Null() {
        assertFalse(passwordValidatorUtil.isValid(null));
    }
}