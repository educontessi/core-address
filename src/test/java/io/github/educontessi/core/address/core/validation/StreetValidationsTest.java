package io.github.educontessi.core.address.core.validation;

import io.github.educontessi.core.address.core.model.Street;
import io.github.educontessi.core.address.core.validation.businessrule.ObjectNotNullValidation;
import io.github.educontessi.core.address.mock.MockSingleton;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit Tests for the class {@link StreetValidations}
 *
 * @author Eduardo Possamai Contessi
 */
class StreetValidationsTest {

    private final MockSingleton mockSingleton = MockSingleton.getInstance();

    @Test
    void validationsOnSave_shouldReturnValidations() {
        // Configuration
        List<Validator> validatorsOutOfCore = mockSingleton.getValidatorsOutOfCore();
        Street model = mockSingleton.getStreet();
        StreetValidations validation = new StreetValidations();
        List<Validator> response;

        // Execution
        response = validation.validationsOnSave(model, validatorsOutOfCore);

        // Check the results
        assertNotNull(response);
        assertEquals(1, response.size());
        assertTrue(response.stream().anyMatch(validator -> validator instanceof ObjectNotNullValidation));
    }

    @Test
    void validationsOnDelete_shouldReturnValidations() {
        // Configuration
        List<Validator> validatorsOutOfCore = mockSingleton.getValidatorsOutOfCore();
        StreetValidations validation = new StreetValidations();
        List<Validator> response;

        // Execution
        response = validation.validationsOnDelete(validatorsOutOfCore);

        // Check the results
        assertNotNull(response);
        assertEquals(0, response.size());
    }

}