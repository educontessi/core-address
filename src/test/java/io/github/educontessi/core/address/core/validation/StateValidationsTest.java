package io.github.educontessi.core.address.core.validation;

import io.github.educontessi.core.address.core.model.State;
import io.github.educontessi.core.address.core.validation.businessrule.ObjectNotNullValidation;
import io.github.educontessi.core.address.mock.MockFactory;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit Tests for the class {@link StateValidations}
 *
 * @author Eduardo Possamai Contessi
 */
class StateValidationsTest {

    private final MockFactory mockFactory = new MockFactory();

    @Test
    void validationsOnSave_shouldReturnValidations() {
        // Configuration
        List<Validator> validatorsOutOfCore = mockFactory.getValidatorsOutOfCore();
        State model = mockFactory.getState();
        StateValidations validation = new StateValidations();
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
        List<Validator> validatorsOutOfCore = mockFactory.getValidatorsOutOfCore();
        StateValidations validation = new StateValidations();
        List<Validator> response;

        // Execution
        response = validation.validationsOnDelete(validatorsOutOfCore);

        // Check the results
        assertNotNull(response);
        assertEquals(0, response.size());
    }

}