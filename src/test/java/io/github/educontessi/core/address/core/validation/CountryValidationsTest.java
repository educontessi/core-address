package io.github.educontessi.core.address.core.validation;

import io.github.educontessi.core.address.core.model.Country;
import io.github.educontessi.core.address.core.validation.businessrule.ObjectNotNullValidation;
import io.github.educontessi.core.address.mock.MockFactory;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit Tests for the class {@link CountryValidations}
 * 
 * @author Eduardo Possamai Contessi
 */
class CountryValidationsTest {

    private final MockFactory mockFactory = new MockFactory();

    @Test
    void validationsOnSave_shouldReturnValidations() {
        // Configuration
        List<Validator> validatorsOutOfCore = mockFactory.getValidatorsOutOfCore();
        Country model = mockFactory.getCountry();
        CountryValidations validation = new CountryValidations();
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
        CountryValidations validation = new CountryValidations();
        List<Validator> response;

        // Execution
        response = validation.validationsOnDelete(validatorsOutOfCore);

        // Check the results
        assertNotNull(response);
        assertEquals(0, response.size());
    }
    
}