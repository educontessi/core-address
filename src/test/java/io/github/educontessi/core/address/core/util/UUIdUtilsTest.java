package io.github.educontessi.core.address.core.util;

import io.github.educontessi.core.address.core.exception.BusinessException;
import io.github.educontessi.core.address.core.exception.InvalidUuidException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit Tests for the class {@link UUIdUtils}
 *
 * @author Eduardo Possamai Contessi
 */
class UUIdUtilsTest {

    @Test
    void shouldThrowIllegalStateException() {
        // Configuration
        String messageException = "Utility class";
        // Execution
        IllegalStateException exception = assertThrows(IllegalStateException.class, UUIdUtils::new);

        // Check the results
        assertNotNull(exception);

        assertEquals(messageException, exception.getMessage());
    }

    @ParameterizedTest
    @CsvSource({
            "fead0150-56e6-46fe-8052-bee2cb9b93a0, FEAD015056E646FE8052BEE2CB9B93A0",
            "771775fc-3628-4532-bc31-896ee4edd155, 771775FC36284532BC31896EE4EDD155",
            "bc6a21f2-52e8-41be-8f9b-e0d407ee6293, BC6A21F252E841BE8F9BE0D407EE6293",
            "dfc0e8ee-679e-4b00-932c-e0e3850dde5b, DFC0E8EE679E4B00932CE0E3850DDE5B"
    })
    void uuidToString(ArgumentsAccessor arguments) {
        // Configuration
        String response;

        // Execution
        response = UUIdUtils.uuidToString(arguments.get(0, UUID.class));

        // Check the results
        assertEquals(arguments.getString(1), response);
    }

    @ParameterizedTest
    @CsvSource({
            "FEAD015056E646FE8052BEE2CB9B93A0, fead0150-56e6-46fe-8052-bee2cb9b93a0",
            "771775fc-3628-4532-bc31-896ee4edd155, 771775fc-3628-4532-bc31-896ee4edd155",
            "BC6A21F252E841BE8F9BE0D407EE6293, bc6a21f2-52e8-41be-8f9b-e0d407ee6293",
            "DFC0E8EE-679E-4B00-932C-E0E3850DDE5B, dfc0e8ee-679e-4b00-932c-e0e3850dde5b"
    })
    void stringToUuid(ArgumentsAccessor arguments) {
        // Configuration
        UUID response;

        // Execution
        response = UUIdUtils.stringToUuid(arguments.getString(0));

        // Check the results
        assertEquals(arguments.get(1, UUID.class), response);
    }

    @ParameterizedTest
    @CsvSource({
            "FEAD015056E646FE8052BEE2CB9B93A0, FEAD0150-56E6-46FE-8052-BEE2CB9B93A0",
            "771775FC36284532BC31896EE4EDD155, 771775FC-3628-4532-BC31-896EE4EDD155",
            "BC6A21F252E841BE8F9BE0D407EE6293, BC6A21F2-52E8-41BE-8F9B-E0D407EE6293",
            "DFC0E8EE679E4B00932CE0E3850DDE5B, DFC0E8EE-679E-4B00-932C-E0E3850DDE5B"
    })
    void formatUUID(ArgumentsAccessor arguments) {
        // Configuration
        String response;

        // Execution
        response = UUIdUtils.formatUUID(arguments.getString(0));

        // Check the results
        assertEquals(arguments.getString(1), response);
    }

    @Test
    void stringToUuid_shouldNotFormatWhenNameIsNull() {
        // Configuration
        InvalidUuidException response;

        // Execution
        response = assertThrows(InvalidUuidException.class, () -> UUIdUtils.stringToUuid(null));

        // Check the results
        assertNotNull(response);
    }

    @Test
    void stringToUuid_shouldNotFormatWhenNameIsEmpty() {
        // Configuration
        InvalidUuidException response;

        // Execution
        response = assertThrows(InvalidUuidException.class, () -> UUIdUtils.stringToUuid(""));

        // Check the results
        assertNotNull(response);
    }

    @Test
    void stringToUuid_shouldNotFormatWhenNameIsInvalid36() {
        // Configuration
        String uuidInvalid = "fead0150-56e6-46fe-8052-bee2cb9b93a00";
        InvalidUuidException response;

        // Execution
        response = assertThrows(InvalidUuidException.class, () -> UUIdUtils.stringToUuid(uuidInvalid));

        // Check the results
        assertNotNull(response);
    }

    @Test
    void stringToUuid_shouldNotFormatWhenNameIsInvalid32() {
        // Configuration
        String uuidInvalid = "fead0150-56e6-46fe-8052-bee2cb9";
        InvalidUuidException response;

        // Execution
        response = assertThrows(InvalidUuidException.class, () -> UUIdUtils.stringToUuid(uuidInvalid));

        // Check the results
        assertNotNull(response);
    }
}