package io.github.educontessi.core.address.core.validation;

import io.github.educontessi.core.address.core.model.Street;
import io.github.educontessi.core.address.core.validation.businessrule.ObjectNotNullValidation;

import java.util.ArrayList;
import java.util.List;

public class StreetValidations {

    public List<Validator> validationsOnSave(Street street, List<Validator> validatorsOutOfCore) {
        List<Validator> validators = new ArrayList<>(validatorsOutOfCore);
        validators.add(new ObjectNotNullValidation(street));
        return validators;
    }

    public List<Validator> validationsOnDelete(List<Validator> validatorsOutOfCore) {
        return new ArrayList<>(validatorsOutOfCore);
    }
}
