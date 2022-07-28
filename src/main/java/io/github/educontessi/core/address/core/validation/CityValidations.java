package io.github.educontessi.core.address.core.validation;

import io.github.educontessi.core.address.core.model.City;
import io.github.educontessi.core.address.core.validation.businessrule.ObjectNotNullValidation;

import java.util.ArrayList;
import java.util.List;

public class CityValidations {

    public List<Validator> validationsOnSave(City city, List<Validator> validatorsOutOfCore) {
        List<Validator> validators = new ArrayList<>(validatorsOutOfCore);
        validators.add(new ObjectNotNullValidation(city));
        return validators;
    }

    public List<Validator> validationsOnDelete(List<Validator> validatorsOutOfCore) {
        return new ArrayList<>(validatorsOutOfCore);
    }
}
