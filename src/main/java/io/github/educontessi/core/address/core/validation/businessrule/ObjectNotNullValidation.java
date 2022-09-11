package io.github.educontessi.core.address.core.validation.businessrule;

import io.github.educontessi.core.address.core.exception.BusinessException;
import io.github.educontessi.core.address.core.validation.Validator;

public class ObjectNotNullValidation implements Validator {

    private final Object object;

    public ObjectNotNullValidation(Object object) {
        this.object = object;
    }

    @Override
    public void validate() {
        if (object == null) {
            throw new BusinessException("model cannot be null");
        }
    }
}
