package io.github.educontessi.core.address.core.validation.businessrule;

import io.github.educontessi.core.address.core.exception.BusinessException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit Tests for the class {@link StringNotEmptyValidation}
 * @author Eduardo Possamai Contessi
 */
class StringNotEmptyValidationTest {

    @Test
    void validate_shouldValidateOk() {
        // Configuration
        String object = "Test";
        StringNotEmptyValidation validation = new StringNotEmptyValidation(object);

        // Execution and Check the results
        assertDoesNotThrow(validation::validate);
        assertNotNull(validation.object());
        assertEquals(object, validation.object());
    }

    @Test
    void validate_shouldValidateNotOk() {
        // Configuration
        String object = "";
        StringNotEmptyValidation validation = new StringNotEmptyValidation(object);
        String messageException = "model cannot be empty";

        // Execution
        BusinessException exception = assertThrows(BusinessException.class, validation::validate);

        // Check the results
        assertNotNull(exception);
        assertEquals(messageException, exception.getMessage());
    }
}