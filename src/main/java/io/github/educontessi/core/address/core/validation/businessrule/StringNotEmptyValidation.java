package io.github.educontessi.core.address.core.validation.businessrule;

import io.github.educontessi.core.address.core.exception.BusinessException;
import io.github.educontessi.core.address.core.validation.Validator;

public class StringNotEmptyValidation implements Validator {

    private final String object;

    public StringNotEmptyValidation(String object) {
        this.object = object;
    }

    @Override
    public void validate() {
        if (object.isEmpty()) {
            throw new BusinessException("model cannot be empty");
        }
    }
}
