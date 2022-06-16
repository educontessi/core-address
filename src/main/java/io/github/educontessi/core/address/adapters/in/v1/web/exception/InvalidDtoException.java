package io.github.educontessi.core.address.adapters.in.v1.web.exception;

public class InvalidDtoException extends RuntimeException {

    public InvalidDtoException(String mensagem) {
        super(mensagem);
    }

    public InvalidDtoException(StringBuilder builder) {
        this(builder.toString().replaceFirst("\\|", ""));
    }

}
