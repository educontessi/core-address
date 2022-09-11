package io.github.educontessi.core.address.adapters.in.web.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Unit Tests for the class {@link InvalidDtoException}
 *
 * @author Eduardo Possamai Contessi
 */
class InvalidDtoExceptionTest {

    @Test
    void shouldThrowInvalidDtoException(){
        // Configuration
        String exceptionMessage = "Exception message";

        // Execution
        InvalidDtoException exception = new InvalidDtoException(exceptionMessage);

        // Check the results
        Assertions.assertNotNull(exception);
        Assertions.assertEquals(exceptionMessage, exception.getMessage());
    }

    @Test
    void shouldThrowInvalidDtoExceptionStringBuilder(){
        // Configuration
        String exceptionMessage = "Exception message";
        StringBuilder builder = new StringBuilder(exceptionMessage);

        // Execution
        InvalidDtoException exception = new InvalidDtoException(builder);

        // Check the results
        Assertions.assertNotNull(exception);
        Assertions.assertEquals(exceptionMessage, exception.getMessage());
    }

}