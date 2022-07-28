package io.github.educontessi.core.address.core.ports.out;

import io.github.educontessi.core.address.core.exception.EntityInUseException;
import io.github.educontessi.core.address.core.exception.EntityNotFoundException;
import io.github.educontessi.core.address.core.filter.StateFilter;
import io.github.educontessi.core.address.core.model.State;

import java.util.List;
import java.util.Optional;

public interface StateRepositoryPort {

    List<State> findAll(String expand);

    Object search(StateFilter filter, Object pageable, String expand);

    Optional<State> findById(Long id, String expand);

    List<State> findAllByCountryId(Long countryId, String expand);

    Optional<State> findByUf(String uf, String expand);

    State save(State model);

    State update(State model, State saved);

    void delete(State saved) throws EntityNotFoundException, EntityInUseException;

}
