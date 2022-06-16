package io.github.educontessi.core.address.core.validation;

import io.github.educontessi.core.address.core.model.Country;
import io.github.educontessi.core.address.core.validation.businessrule.ObjectNotNull;

import java.util.ArrayList;
import java.util.List;

public class CountryValidations {

    public List<Validator> validationsOnSave(Country country, List<Validator> validatorsOutOfCore) {
        List<Validator> validators = new ArrayList<>(validatorsOutOfCore);
        validators.add(new ObjectNotNull(country));
        return validators;
    }

    public List<Validator> validationsOnDelete(List<Validator> validatorsOutOfCore) {
        return new ArrayList<>(validatorsOutOfCore);
    }
}
