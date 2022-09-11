package io.github.educontessi.core.address.adapters.in.web.handler;

import io.github.educontessi.core.address.adapters.in.web.exception.InvalidDtoException;
import io.github.educontessi.core.address.adapters.in.web.response.ResponseError;
import io.github.educontessi.core.address.core.exception.BusinessException;
import io.github.educontessi.core.address.core.exception.EntityInUseException;
import io.github.educontessi.core.address.core.exception.EntityNotFoundException;
import io.github.educontessi.core.address.core.exception.InvalidUuidException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Unit Tests for the class {@link RestExceptionHandler}
 *
 * @author Eduardo Possamai Contessi
 */
@ExtendWith(SpringExtension.class)
class RestExceptionHandlerTest {

    @Mock
    private WebRequest webRequest;

    @Test
    void handleHttpMediaTypeNotSupported() {
        // Configuration
        RestExceptionHandler restExceptionHandler = new RestExceptionHandler();
        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        String userMessage = "Content-Type was not informed";

        HttpMediaTypeNotSupportedException mockException = mock(HttpMediaTypeNotSupportedException.class);
        when(mockException.getMessage()).thenReturn("Exception message");

        // Execution
        ResponseEntity<Object> response = restExceptionHandler.handleHttpMediaTypeNotSupported(mockException, headers, status, webRequest);

        // Check the results
        assertNotNull(response);
        assertTrue(response.getBody() instanceof ResponseError);
        assertEquals(415, response.getStatusCodeValue());
        assertEquals(userMessage, ((ResponseError) response.getBody()).getUserMessage());
    }

    @Test
    void handleHttpMessageNotReadable() {
        // Configuration
        RestExceptionHandler restExceptionHandler = new RestExceptionHandler();
        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        String userMessage = "The request body is invalid. Check syntax error.";

        HttpMessageNotReadableException mockException = mock(HttpMessageNotReadableException.class);
        when(mockException.getMessage()).thenReturn("Exception message");

        // Execution
        ResponseEntity<Object> response = restExceptionHandler.handleHttpMessageNotReadable(mockException, headers, status, webRequest);

        // Check the results
        assertNotNull(response);
        assertTrue(response.getBody() instanceof ResponseError);
        assertEquals(400, response.getStatusCodeValue());
        assertEquals(userMessage, ((ResponseError) response.getBody()).getUserMessage());
    }

    @Test
    void handleBusinessException() {
        // Configuration
        RestExceptionHandler restExceptionHandler = new RestExceptionHandler();

        BusinessException mockException = mock(BusinessException.class);
        when(mockException.getMessage()).thenReturn("Exception message");

        // Execution
        ResponseEntity<Object> response = restExceptionHandler.handleBusinessException(mockException, webRequest);

        // Check the results
        assertNotNull(response);
        assertTrue(response.getBody() instanceof ResponseError);
        assertEquals(400, response.getStatusCodeValue());
        assertEquals("Exception message", ((ResponseError) response.getBody()).getUserMessage());
    }


    @Test
    void handleEntityInUseException() {
        // Configuration
        RestExceptionHandler restExceptionHandler = new RestExceptionHandler();

        EntityInUseException mockException = mock(EntityInUseException.class);
        when(mockException.getMessage()).thenReturn("Exception message");

        // Execution
        ResponseEntity<Object> response = restExceptionHandler.handleEntityInUseException(mockException, webRequest);

        // Check the results
        assertNotNull(response);
        assertTrue(response.getBody() instanceof ResponseError);
        assertEquals(409, response.getStatusCodeValue());
        assertEquals("Exception message", ((ResponseError) response.getBody()).getUserMessage());
    }

    @Test
    void handleEntityNotFoundException() {
        // Configuration
        RestExceptionHandler restExceptionHandler = new RestExceptionHandler();

        EntityNotFoundException mockException = mock(EntityNotFoundException.class);
        when(mockException.getMessage()).thenReturn("Exception message");

        // Execution
        ResponseEntity<Object> response = restExceptionHandler.handleEntityNotFoundException(mockException, webRequest);

        // Check the results
        assertNotNull(response);
        assertTrue(response.getBody() instanceof ResponseError);
        assertEquals(404, response.getStatusCodeValue());
        assertEquals("Exception message", ((ResponseError) response.getBody()).getUserMessage());
    }

    @Test
    void handleInvalidUuidException() {
        // Configuration
        RestExceptionHandler restExceptionHandler = new RestExceptionHandler();

        InvalidUuidException mockException = mock(InvalidUuidException.class);
        when(mockException.getMessage()).thenReturn("Exception message");

        // Execution
        ResponseEntity<Object> response = restExceptionHandler.handleInvalidUuidException(mockException, webRequest);

        // Check the results
        assertNotNull(response);
        assertTrue(response.getBody() instanceof ResponseError);
        assertEquals(400, response.getStatusCodeValue());
        assertEquals("Exception message", ((ResponseError) response.getBody()).getUserMessage());
    }

    @Test
    void handleInvalidDtoException() {
        // Configuration
        RestExceptionHandler restExceptionHandler = new RestExceptionHandler();

        InvalidDtoException mockException = mock(InvalidDtoException.class);
        when(mockException.getMessage()).thenReturn("Exception message");

        // Execution
        ResponseEntity<Object> response = restExceptionHandler.handleInvalidDtoException(mockException, webRequest);

        // Check the results
        assertNotNull(response);
        assertTrue(response.getBody() instanceof ResponseError);
        assertEquals(422, response.getStatusCodeValue());
        assertEquals("Exception message", ((ResponseError) response.getBody()).getUserMessage());
    }

    @Test
    void handleMethodArgumentTypeMismatchException() {
        // Configuration
        RestExceptionHandler restExceptionHandler = new RestExceptionHandler();

        MethodArgumentTypeMismatchException mockException = mock(MethodArgumentTypeMismatchException.class);
        when(mockException.getMessage()).thenReturn("Exception message");

        // Execution
        ResponseEntity<Object> response = restExceptionHandler.handleMethodArgumentTypeMismatchException(mockException, webRequest);

        // Check the results
        assertNotNull(response);
        assertTrue(response.getBody() instanceof ResponseError);
        assertEquals(422, response.getStatusCodeValue());
        assertEquals("Exception message", ((ResponseError) response.getBody()).getUserMessage());
    }

    @Test
    void handleBindException() {
        // Configuration
        RestExceptionHandler restExceptionHandler = new RestExceptionHandler();

        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        BindException mockException = mock(BindException.class);
        when(mockException.getMessage()).thenReturn("Exception message");

        // Execution
        ResponseEntity<Object> response = restExceptionHandler.handleBindException(mockException, headers, status, webRequest);

        // Check the results
        assertNotNull(response);
        assertTrue(response.getBody() instanceof ResponseError);
        assertEquals(422, response.getStatusCodeValue());
        assertEquals("Exception message", ((ResponseError) response.getBody()).getUserMessage());
    }

    @Test
    void handleUncaught() {
        // Configuration
        RestExceptionHandler restExceptionHandler = new RestExceptionHandler();
        String userMessage = "An unexpected internal system error has occurred. Please try again and if the problem persists, contact your system administrator.";

        Exception mockException = mock(Exception.class);
        when(mockException.getMessage()).thenReturn("Exception message");

        // Execution
        ResponseEntity<Object> response = restExceptionHandler.handleUncaught(mockException, webRequest);

        // Check the results
        assertNotNull(response);
        assertTrue(response.getBody() instanceof ResponseError);
        assertEquals(500, response.getStatusCodeValue());
        assertEquals(userMessage, ((ResponseError) response.getBody()).getUserMessage());
    }

    @Test
    void handleDataIntegrityViolationException() {
        // Configuration
        RestExceptionHandler restExceptionHandler = new RestExceptionHandler();
        String userMessage = "Exception message";

        DataIntegrityViolationException mockException = mock(DataIntegrityViolationException.class);
        when(mockException.getMessage()).thenReturn("Exception message");

        // Execution
        ResponseEntity<Object> response = restExceptionHandler.handleDataIntegrityViolationException(mockException, webRequest);

        // Check the results
        assertNotNull(response);
        assertTrue(response.getBody() instanceof ResponseError);
        assertEquals(400, response.getStatusCodeValue());
        assertEquals(userMessage, ((ResponseError) response.getBody()).getUserMessage());
    }
}