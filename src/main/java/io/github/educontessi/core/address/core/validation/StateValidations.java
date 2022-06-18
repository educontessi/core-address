package io.github.educontessi.core.address.core.validation;

import io.github.educontessi.core.address.core.model.State;
import io.github.educontessi.core.address.core.validation.businessrule.ObjectNotNull;

import java.util.ArrayList;
import java.util.List;

public class StateValidations {

    public List<Validator> validationsOnSave(State state, List<Validator> validatorsOutOfCore) {
        List<Validator> validators = new ArrayList<>(validatorsOutOfCore);
        validators.add(new ObjectNotNull(state));
        return validators;
    }

    public List<Validator> validationsOnDelete(List<Validator> validatorsOutOfCore) {
        return new ArrayList<>(validatorsOutOfCore);
    }
}
