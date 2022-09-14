package io.github.educontessi.core.address.core.validation.businessrule;

import io.github.educontessi.core.address.core.exception.BusinessException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit Tests for the class {@link StringLengthValidation}
 *
 * @author Eduardo Possamai Contessi
 */
class StringLengthValidationTest {

    @Test
    void validate_shouldValidateOk() {
        // Configuration
        String object = "Test";
        int length = 4;
        StringLengthValidation validation = new StringLengthValidation(object, length);

        // Execution and Check the results
        assertDoesNotThrow(validation::validate);
        assertNotNull(validation.object());
        assertEquals(object, validation.object());
        assertEquals(length, validation.length());
    }

    @Test
    void validate_shouldValidateNotOk() {
        // Configuration
        String object = "Test";
        int length = 5;
        StringLengthValidation validation = new StringLengthValidation(object, length);
        String messageException = "expected length: " + length;

        // Execution
        BusinessException exception = assertThrows(BusinessException.class, validation::validate);

        // Check the results
        assertNotNull(exception);
        assertEquals(messageException, exception.getMessage());
    }

}