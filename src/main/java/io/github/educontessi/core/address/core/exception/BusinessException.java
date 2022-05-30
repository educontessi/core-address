package io.github.educontessi.core.address.core.exception;

public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable reason) {
        super(message, reason);
    }

}
