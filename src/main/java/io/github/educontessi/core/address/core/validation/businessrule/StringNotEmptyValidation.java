package io.github.educontessi.core.address.core.validation.businessrule;

import io.github.educontessi.core.address.core.exception.BusinessException;
import io.github.educontessi.core.address.core.validation.Validator;

public record StringNotEmptyValidation(String object) implements Validator {

    @Override
    public void validate() {
        if (object.isEmpty()) {
            throw new BusinessException("model cannot be empty");
        }
    }
}
