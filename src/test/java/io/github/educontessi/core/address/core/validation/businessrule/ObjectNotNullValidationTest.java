package io.github.educontessi.core.address.core.validation.businessrule;

import io.github.educontessi.core.address.core.exception.BusinessException;
import io.github.educontessi.core.address.core.model.Country;
import io.github.educontessi.core.address.mock.MockFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit Tests for the class {@link ObjectNotNullValidation}
 *
 * @author Eduardo Possamai Contessi
 */
class ObjectNotNullValidationTest {

    private final MockFactory mockFactory = new MockFactory();

    @Test
    void validate_shouldValidateOk() {
        // Configuration
        Country country = mockFactory.getCountry();
        ObjectNotNullValidation validation = new ObjectNotNullValidation(country);

        // Execution and Check the results
        assertDoesNotThrow(validation::validate);
        assertNotNull(validation.object());
    }

    @Test
    void validate_shouldValidateNotOk() {
        // Configuration
        ObjectNotNullValidation validation = new ObjectNotNullValidation(null);
        String messageException = "model cannot be null";

        // Execution
        BusinessException exception = assertThrows(BusinessException.class, validation::validate);

        // Check the results
        assertNotNull(exception);
        assertEquals(messageException, exception.getMessage());
    }

}