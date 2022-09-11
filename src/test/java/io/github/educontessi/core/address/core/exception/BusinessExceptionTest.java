package io.github.educontessi.core.address.core.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Unit Tests for the class {@link BusinessException}
 *
 * @author Eduardo Possamai Contessi
 */
class BusinessExceptionTest {

    @Test
    void shouldThrowBusinessException(){
        // Configuration
        String exceptionMessage = "Exception message";

        // Execution
        BusinessException exception = new BusinessException(exceptionMessage);

        // Check the results
        Assertions.assertNotNull(exception);
        Assertions.assertEquals(exceptionMessage, exception.getMessage());
    }

    @Test
    void shouldThrowBusinessExceptionWithThrowable(){
        // Configuration
        String exceptionMessage = "Exception message";
        Throwable throwable = new Throwable(String.valueOf(1L));

        // Execution
        BusinessException exception = new BusinessException(exceptionMessage, throwable);

        // Check the results
        Assertions.assertNotNull(exception);
        Assertions.assertNotNull(exception.getCause());
        Assertions.assertEquals(exceptionMessage, exception.getMessage());
    }
}