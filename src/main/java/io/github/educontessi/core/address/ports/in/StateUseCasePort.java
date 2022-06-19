package io.github.educontessi.core.address.ports.in;

import io.github.educontessi.core.address.core.filter.StateFilter;
import io.github.educontessi.core.address.core.model.State;
import io.github.educontessi.core.address.core.validation.Validator;

import java.util.List;

public interface StateUseCasePort {

    List<State> findAll(String expand);

    Object search(StateFilter filter, Object pageable);

    State findById(Long id);

    State save(State model, List<Validator> validatorsOutOfCore);

    State update(Long id, State model, List<Validator> validatorsOutOfCore);

    void delete(Long id, List<Validator> validatorsOutOfCore);
}
