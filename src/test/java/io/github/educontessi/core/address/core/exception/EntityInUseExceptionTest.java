package io.github.educontessi.core.address.core.exception;

import io.github.educontessi.core.address.core.util.UUIdUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

/**
 * Unit Tests for the class {@link EntityInUseException}
 *
 * @author Eduardo Possamai Contessi
 */
class EntityInUseExceptionTest {

    @Test
    void shouldThrowEntityInUseException() {
        // Configuration
        String exceptionMessage = "Exception message";

        // Execution
        EntityInUseException exception = new EntityInUseException(exceptionMessage);

        // Check the results
        Assertions.assertNotNull(exception);
        Assertions.assertEquals(exceptionMessage, exception.getMessage());
    }

    @Test
    void shouldThrowEntityInUseExceptionWithLongId() {
        // Configuration
        Long id = 1L;
        String exceptionMessage = "Code registration " + id + " cannot be removed as it is in use";

        // Execution
        EntityInUseException exception = new EntityInUseException(id);

        // Check the results
        Assertions.assertNotNull(exception);
        Assertions.assertEquals(exceptionMessage, exception.getMessage());
    }

    @Test
    void shouldThrowEntityInUseExceptionWithUUID() {
        // Configuration
        UUID id = UUID.randomUUID();
        String exceptionMessage = "Code registration " + UUIdUtils.uuidToString(id) + " cannot be removed as it is in use";

        // Execution
        EntityInUseException exception = new EntityInUseException(id);

        // Check the results
        Assertions.assertNotNull(exception);
        Assertions.assertEquals(exceptionMessage, exception.getMessage());
    }

}