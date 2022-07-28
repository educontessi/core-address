package io.github.educontessi.core.address.core.validation;

import io.github.educontessi.core.address.core.model.Neighborhood;
import io.github.educontessi.core.address.core.validation.businessrule.ObjectNotNullValidation;

import java.util.ArrayList;
import java.util.List;

public class NeighborhoodValidations {

    public List<Validator> validationsOnSave(Neighborhood neighborhood, List<Validator> validatorsOutOfCore) {
        List<Validator> validators = new ArrayList<>(validatorsOutOfCore);
        validators.add(new ObjectNotNullValidation(neighborhood));
        return validators;
    }

    public List<Validator> validationsOnDelete(List<Validator> validatorsOutOfCore) {
        return new ArrayList<>(validatorsOutOfCore);
    }
}
