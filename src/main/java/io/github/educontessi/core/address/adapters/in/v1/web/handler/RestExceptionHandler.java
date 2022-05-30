package io.github.educontessi.core.address.adapters.in.v1.web.handler;

import io.github.educontessi.core.address.adapters.in.v1.web.response.ErrorDetail;
import io.github.educontessi.core.address.adapters.in.v1.web.response.ErrorType;
import io.github.educontessi.core.address.adapters.in.v1.web.response.RequestError;
import io.github.educontessi.core.address.core.exception.BusinessException;
import io.github.educontessi.core.address.core.exception.EntityInUseException;
import io.github.educontessi.core.address.core.exception.EntityNotFoundException;
import io.github.educontessi.core.address.core.exception.InvalidUuidException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Classe de tratamento de erros
 *
 * @author Eduardo Possamai Contessi
 */
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    public static final String GENERIC_ERROR_MESSAGE_TO_END_USER
            = "An unexpected internal system error has occurred. Please try again and if "
            + "the problem persists, contact your system administrator.";

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        HttpStatus statusResponse = HttpStatus.UNSUPPORTED_MEDIA_TYPE;
        ErrorType errorType = ErrorType.INVALID_REQUEST;
        String userMessage = "Content-Type was not informed";

        RequestError requestError = getRequestError(ex, statusResponse, errorType, userMessage);
        return handleExceptionInternal(ex, requestError, headers, statusResponse, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        HttpStatus statusResponse = HttpStatus.BAD_REQUEST;
        ErrorType errorType = ErrorType.INCOMPREHENSIVE_MESSAGE;
        String userMessage = "The request body is invalid. Check syntax error.";

        RequestError requestError = getRequestError(ex, statusResponse, errorType, userMessage);
        return handleExceptionInternal(ex, requestError, headers, statusResponse, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) { // Exception usada quando não utiliza dto
        HttpStatus statusResponse = HttpStatus.BAD_REQUEST;
        ErrorType errorType = ErrorType.INVALID_DATA;
        String userMessage = "One or more fields are invalid. Please fill in correctly and try again.";

        RequestError requestError = getRequestError(ex, statusResponse, errorType, userMessage);
        requestError.setDatails(getErrorDatails(ex.getBindingResult()));
        return handleExceptionInternal(ex, requestError, headers, statusResponse, request);
    }

    @ExceptionHandler({BusinessException.class})
    public ResponseEntity<Object> handleBusinessException(BusinessException ex, WebRequest request) {
        HttpStatus statusResponse = HttpStatus.BAD_REQUEST;
        ErrorType errorType = ErrorType.BUSINESS_ERROR;
        String userMessage = ex.getMessage();

        RequestError requestError = getRequestError(ex, statusResponse, errorType, userMessage);
        return handleExceptionInternal(ex, requestError, new HttpHeaders(), statusResponse, request);
    }

    @ExceptionHandler({EntityInUseException.class})
    public ResponseEntity<Object> handleEntityInUseException(EntityInUseException ex, WebRequest request) {
        HttpStatus statusResponse = HttpStatus.CONFLICT;
        ErrorType errorType = ErrorType.ENTITY_IN_USE;
        String userMessage = ex.getMessage();

        RequestError requestError = getRequestError(ex, statusResponse, errorType, userMessage);
        return handleExceptionInternal(ex, requestError, new HttpHeaders(), statusResponse, request);
    }

    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex, WebRequest request) {
        HttpStatus statusResponse = HttpStatus.NOT_FOUND;
        ErrorType errorType = ErrorType.RESOURCE_NOT_FOUND;
        String userMessage = ex.getMessage();

        RequestError requestError = getRequestError(ex, statusResponse, errorType, userMessage);
        return handleExceptionInternal(ex, requestError, new HttpHeaders(), statusResponse, request);
    }

    @ExceptionHandler({InvalidUuidException.class})
    public ResponseEntity<Object> handleInvalidUuidException(InvalidUuidException ex, WebRequest request) {
        HttpStatus statusResponse = HttpStatus.BAD_REQUEST;
        ErrorType errorType = ErrorType.INVALID_DATA;
        String userMessage = ex.getMessage();

        RequestError requestError = getRequestError(ex, statusResponse, errorType, userMessage);
        return handleExceptionInternal(ex, requestError, new HttpHeaders(), statusResponse, request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleUncaught(Exception ex, WebRequest request) {
        HttpStatus statusResponse = HttpStatus.INTERNAL_SERVER_ERROR;
        ErrorType errorType = ErrorType.INTERNAL_SERVER_ERROR;

        RequestError requestError = getRequestError(ex, statusResponse, errorType, GENERIC_ERROR_MESSAGE_TO_END_USER);
        return handleExceptionInternal(ex, requestError, new HttpHeaders(), statusResponse, request);
    }

    protected RequestError getRequestError(Exception ex, HttpStatus status, ErrorType errorType, String userMessage) {
        RequestError requestError = new RequestError();
        requestError.setStatus(status.value());
        requestError.setTimestamp(OffsetDateTime.now());
        requestError.setType(errorType.name());
        requestError.setTitle(errorType.getTitle());
        requestError.setUrlError(errorType.getPath());
        requestError.setUserMessage(userMessage);
        requestError.setException(ex.getClass().getName());
        requestError.setExceptionMessage(ex.getMessage());

        return requestError;
    }

    protected List<ErrorDetail> getErrorDatails(BindingResult bindingResult) {
        List<ErrorDetail> erros = new ArrayList<>();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            var field = fieldError.getField();
            var userMessage = Objects.requireNonNull(fieldError.getDefaultMessage()).replace("{0}", fieldError.getField());
            var exceptionMessage = fieldError.toString();
            erros.add(new ErrorDetail(field, userMessage, exceptionMessage));
        }
        return erros;
    }

}
