package io.github.educontessi.core.address.core.exception;

import io.github.educontessi.core.address.core.util.UUIdUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

/**
 * Unit Tests for the class {@link EntityNotFoundException}
 *
 * @author Eduardo Possamai Contessi
 */
class EntityNotFoundExceptionTest {

    @Test
    void shouldThrowEntityNotFoundException() {
        // Configuration
        String exceptionMessage = "Exception message";

        // Execution
        EntityNotFoundException exception = new EntityNotFoundException(exceptionMessage);

        // Check the results
        Assertions.assertNotNull(exception);
        Assertions.assertEquals(exceptionMessage, exception.getMessage());
    }

    @Test
    void shouldThrowEntityNotFoundExceptionWithLongId() {
        // Configuration
        Long id = 1L;
        String exceptionMessage = "There is no record with code " + id;

        // Execution
        EntityNotFoundException exception = new EntityNotFoundException(id);

        // Check the results
        Assertions.assertNotNull(exception);
        Assertions.assertEquals(exceptionMessage, exception.getMessage());
    }

    @Test
    void shouldThrowEntityNotFoundExceptionWithUUID() {
        // Configuration
        UUID id = UUID.randomUUID();
        String exceptionMessage = "There is no record with code " + UUIdUtils.uuidToString(id);

        // Execution
        EntityNotFoundException exception = new EntityNotFoundException(id);

        // Check the results
        Assertions.assertNotNull(exception);
        Assertions.assertEquals(exceptionMessage, exception.getMessage());
    }

}