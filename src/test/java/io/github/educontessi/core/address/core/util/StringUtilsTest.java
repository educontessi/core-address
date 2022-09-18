package io.github.educontessi.core.address.core.util;

import io.github.educontessi.core.address.core.exception.BusinessException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit Tests for the class {@link StringUtils}
 *
 * @author Eduardo Possamai Contessi
 */
class StringUtilsTest {

    @Test
    void shouldThrowIllegalStateException() {
        // Configuration
        String messageException = "Utility class";
        // Execution
        IllegalStateException exception = assertThrows(IllegalStateException.class, StringUtils::new);

        // Check the results
        assertNotNull(exception);

        assertEquals(messageException, exception.getMessage());
    }

    @ParameterizedTest
    @CsvSource({
            "joão Da SILVA, João da Silva",
            "PEDRO DE soUZA, Pedro de Souza",
            "são miguel d'oeste, São Miguel D'Oeste",
            "caixa D'Água, Caixa D'Água"
    })
    void formatName(ArgumentsAccessor arguments) {
        // Configuration
        String response;

        // Execution
        response = StringUtils.formatName(arguments.getString(0));

        // Check the results
        assertEquals(arguments.getString(1), response);
    }

    @Test
    void formatName_shouldNotFormatWhenNameIsNull() {
        // Configuration
        String response;

        // Execution
        response = StringUtils.formatName(null);

        // Check the results
        assertNull(response);
    }

    @Test
    void formatName_shouldNotFormatWhenNameIsInvalid() {
        // Configuration
        String name = "São Miguel D''Oeste";
        String messageException = "Invalid name";
        BusinessException response;

        // Execution
        response = assertThrows(BusinessException.class, () -> StringUtils.formatName(name));

        // Check the results
        assertNotNull(response);
        assertEquals(messageException, response.getMessage());
    }

    @ParameterizedTest
    @CsvSource({
            "Test description 1, Test description 1",
            "Test     description 2, Test description 2",
            "Test description      3, Test description 3",
            "      Test description 4, Test description 4",
            "      Test             description              5        , Test description 5"
    })
    void formatDescription(ArgumentsAccessor arguments) {
        // Configuration
        String response;

        // Execution
        response = StringUtils.formatDescription(arguments.getString(0));

        // Check the results
        assertEquals(arguments.getString(1), response);
    }

    @Test
    void formatDescription_shouldNotFormatWhenDescriptionIsNull() {
        // Configuration
        String response;

        // Execution
        response = StringUtils.formatDescription(null);

        // Check the results
        assertNull(response);
    }

    @ParameterizedTest
    @CsvSource(value = {
            ", true",
            "'', true",
            "Test, false"
    })
    void nullOrEmpty(ArgumentsAccessor arguments) {
        // Configuration
        boolean response;

        // Execution
        response = StringUtils.nullOrEmpty(arguments.getString(0));

        // Check the results
        assertEquals(arguments.getBoolean(1), response);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "da, true",
            "das, true",
            "de, true",
            "do, true",
            "dos, true",
            "e, true",
            "capivara, false",
    })
    void checkPrepositionName(ArgumentsAccessor arguments) {
        // Configuration
        boolean response;

        // Execution
        response = StringUtils.checkPrepositionName(arguments.getString(0));

        // Check the results
        assertEquals(arguments.getBoolean(1), response);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "213321$3212#123213#123123#, 2133213212123213123123",
            "000.000.000-00, 00000000000",
            "fead0150-56e6-46fe-8052-bee2cb9b93a0, 015056646805229930"
    })
    void removeNumberMask(ArgumentsAccessor arguments) {
        // Configuration
        String response;

        // Execution
        response = StringUtils.removeNumberMask(arguments.getString(0));

        // Check the results
        assertEquals(arguments.getString(1), response);
    }

    @Test
    void removeNumberMask_shouldNotFormatWhenValueIsNull() {
        // Configuration
        String response;

        // Execution
        response = StringUtils.removeNumberMask(null);

        // Check the results
        assertNull(response);
    }
}