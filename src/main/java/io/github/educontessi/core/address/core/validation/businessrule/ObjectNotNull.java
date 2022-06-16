package io.github.educontessi.core.address.core.validation.businessrule;

import io.github.educontessi.core.address.core.validation.Validator;

import java.util.Objects;

public class ObjectNotNull implements Validator {

    private final Object object;

    public ObjectNotNull(Object object) {
        this.object = object;
    }

    @Override
    public void validate() {
        Objects.requireNonNull(object, "model cannot be null");
    }
}
