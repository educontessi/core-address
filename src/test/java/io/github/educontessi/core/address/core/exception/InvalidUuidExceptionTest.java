package io.github.educontessi.core.address.core.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Unit Tests for the class {@link InvalidUuidException}
 *
 * @author Eduardo Possamai Contessi
 */
class InvalidUuidExceptionTest {

    @Test
    void shouldThrowInvalidUuidException() {
        // Configuration
        String exceptionMessage = "Exception message";

        // Execution
        InvalidUuidException exception = new InvalidUuidException(exceptionMessage);

        // Check the results
        Assertions.assertNotNull(exception);
        Assertions.assertEquals(exceptionMessage, exception.getMessage());
    }

}