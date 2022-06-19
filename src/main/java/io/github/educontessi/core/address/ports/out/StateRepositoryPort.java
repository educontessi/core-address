package io.github.educontessi.core.address.ports.out;

import io.github.educontessi.core.address.core.exception.EntityInUseException;
import io.github.educontessi.core.address.core.exception.EntityNotFoundException;
import io.github.educontessi.core.address.core.filter.StateFilter;
import io.github.educontessi.core.address.core.model.State;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface StateRepositoryPort {

    List<State> findAll(String expand);

    Page<State> search(StateFilter filter, Object pageable);

    Optional<State> findById(Long id);

    State save(State model);

    State update(State model, State saved);

    void delete(State saved) throws EntityNotFoundException, EntityInUseException;

}