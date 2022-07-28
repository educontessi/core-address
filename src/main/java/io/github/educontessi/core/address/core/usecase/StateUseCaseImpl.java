package io.github.educontessi.core.address.core.usecase;

import io.github.educontessi.core.address.core.exception.EntityNotFoundException;
import io.github.educontessi.core.address.core.filter.StateFilter;
import io.github.educontessi.core.address.core.model.State;
import io.github.educontessi.core.address.core.validation.StateValidations;
import io.github.educontessi.core.address.core.validation.Validator;
import io.github.educontessi.core.address.core.ports.in.StateUseCasePort;
import io.github.educontessi.core.address.core.ports.out.StateRepositoryPort;

import java.util.List;
import java.util.Optional;

public class StateUseCaseImpl implements StateUseCasePort {

    private final StateRepositoryPort repository;
    private final StateValidations validations;

    public StateUseCaseImpl(StateRepositoryPort repository, StateValidations validations) {
        this.repository = repository;
        this.validations = validations;
    }

    @Override
    public List<State> findAll(String expand) {
        return repository.findAll(expand);
    }

    @Override
    public Object search(StateFilter filter, Object pageable, String expand) {
        return repository.search(filter, pageable, expand);
    }

    @Override
    public State findById(Long id, String expand) {
        Optional<State> optionalSaved = repository.findById(id, expand);
        return optionalSaved.orElseThrow(() -> new EntityNotFoundException(id));
    }

    public State findById(Long id) {
        return findById(id, null);
    }

    @Override
    public List<State> findAllByCountryId(Long countryId, String expand) {
        return repository.findAllByCountryId(countryId, expand);
    }

    @Override
    public State findByUf(String uf, String expand) {
        Optional<State> optionalSaved = repository.findByUf(uf, expand);
        return optionalSaved.orElseThrow(() -> new EntityNotFoundException(String.format("There is no record with UF %s", uf)));
    }

    @Override
    public State save(State model, List<Validator> validatorsOutOfCore) {
        validations.validationsOnSave(model, validatorsOutOfCore).forEach(Validator::validate);
        return repository.save(model);
    }

    @Override
    public State update(Long id, State model, List<Validator> validatorsOutOfCore) {
        validations.validationsOnSave(model, validatorsOutOfCore).forEach(Validator::validate);
        State saved = findById(id);
        return repository.update(model, saved);
    }

    @Override
    public void delete(Long id, List<Validator> validatorsOutOfCore) {
        validations.validationsOnDelete(validatorsOutOfCore).forEach(Validator::validate);
        State saved = findById(id);
        repository.delete(saved);
    }

}
