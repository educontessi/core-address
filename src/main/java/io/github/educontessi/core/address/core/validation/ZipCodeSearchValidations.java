package io.github.educontessi.core.address.core.validation;

import io.github.educontessi.core.address.core.validation.businessrule.ObjectNotNullValidation;
import io.github.educontessi.core.address.core.validation.businessrule.StringLengthValidation;
import io.github.educontessi.core.address.core.validation.businessrule.StringNotEmptyValidation;

import java.util.ArrayList;
import java.util.List;

public class ZipCodeSearchValidations {

    public List<Validator> validationsOnSearch(String zipCode) {
        List<Validator> validators = new ArrayList<>();
        validators.add(new ObjectNotNullValidation(zipCode));
        validators.add(new StringNotEmptyValidation(zipCode));
        validators.add(new StringLengthValidation(zipCode, 8));
        return validators;
    }

}
