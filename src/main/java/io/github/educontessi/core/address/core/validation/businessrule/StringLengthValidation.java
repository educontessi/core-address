package io.github.educontessi.core.address.core.validation.businessrule;

import io.github.educontessi.core.address.core.exception.BusinessException;
import io.github.educontessi.core.address.core.validation.Validator;

public record StringLengthValidation(String object, int length) implements Validator {

    @Override
    public void validate() {
        if (object.length() != length) {
            throw new BusinessException("expected length: " + length);
        }
    }
}
