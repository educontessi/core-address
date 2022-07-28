package io.github.educontessi.core.address.core.validation.businessrule;

import io.github.educontessi.core.address.core.exception.BusinessException;
import io.github.educontessi.core.address.core.validation.Validator;

public class StringLengthValidation implements Validator {

    private final String object;
    private final int length;

    public StringLengthValidation(String object, int length) {
        this.object = object;
        this.length = length;
    }

    @Override
    public void validate() {
        if (object.length() != length) {
            throw new BusinessException("expected length: " + length);
        }
    }
}
