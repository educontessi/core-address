package io.github.educontessi.core.address.core.validation;

import io.github.educontessi.core.address.core.validation.businessrule.ObjectNotNullValidation;
import io.github.educontessi.core.address.core.validation.businessrule.StringLengthValidation;
import io.github.educontessi.core.address.core.validation.businessrule.StringNotEmptyValidation;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit Tests for the class {@link ZipCodeSearchValidations}
 *
 * @author Eduardo Possamai Contessi
 */
class ZipCodeSearchValidationsTest {

    @Test
    void validationsOnSave_shouldReturnValidations() {
        // Configuration
        String zipcode = "88900000";
        ZipCodeSearchValidations validation = new ZipCodeSearchValidations();
        List<Validator> response;

        // Execution
        response = validation.validationsOnSearch(zipcode);

        // Check the results
        assertNotNull(response);
        assertEquals(3, response.size());
        assertTrue(response.stream().anyMatch(validator -> validator instanceof ObjectNotNullValidation));
        assertTrue(response.stream().anyMatch(validator -> validator instanceof StringNotEmptyValidation));
        assertTrue(response.stream().anyMatch(validator -> validator instanceof StringLengthValidation));
    }

}