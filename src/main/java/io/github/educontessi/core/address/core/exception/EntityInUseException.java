package io.github.educontessi.core.address.core.exception;

import io.github.educontessi.core.address.core.util.UUIdUtils;

import java.io.Serial;
import java.util.UUID;

public class EntityInUseException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -4390195150636265400L;

    public EntityInUseException(String mensagem) {
        super(mensagem);
    }

    public EntityInUseException(Long id) {
        this(String.format("Code registration %d cannot be removed as it is in use", id));
    }

    public EntityInUseException(UUID id) {
        this(String.format("Code registration %s cannot be removed as it is in use", UUIdUtils.uuidToString(id)));
    }

}
