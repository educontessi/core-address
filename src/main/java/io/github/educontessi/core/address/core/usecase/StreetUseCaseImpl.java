package io.github.educontessi.core.address.core.usecase;

import io.github.educontessi.core.address.core.exception.EntityNotFoundException;
import io.github.educontessi.core.address.core.filter.StreetFilter;
import io.github.educontessi.core.address.core.model.Street;
import io.github.educontessi.core.address.core.ports.in.StreetUseCasePort;
import io.github.educontessi.core.address.core.ports.out.StreetRepositoryPort;
import io.github.educontessi.core.address.core.validation.StreetValidations;
import io.github.educontessi.core.address.core.validation.Validator;

import java.util.List;
import java.util.Optional;

public class StreetUseCaseImpl implements StreetUseCasePort {

    private final StreetRepositoryPort repository;
    private final StreetValidations validations;

    public StreetUseCaseImpl(StreetRepositoryPort repository, StreetValidations validations) {
        this.repository = repository;
        this.validations = validations;
    }

    @Override
    public List<Street> findAll(String expand) {
        return repository.findAll(expand);
    }

    @Override
    public Object search(StreetFilter filter, Object pageable, String expand) {
        return repository.search(filter, pageable, expand);
    }

    @Override
    public Street findById(Long id, String expand) {
        Optional<Street> optionalSaved = repository.findById(id, expand);
        return optionalSaved.orElseThrow(() -> new EntityNotFoundException(id));
    }

    public Street findById(Long id) {
        return findById(id, null);
    }

    @Override
    public List<Street> findAllByCityId(Long cityId, String expand) {
        return repository.findAllByCityId(cityId, expand);
    }

    @Override
    public Street save(Street model, List<Validator> validatorsOutOfCore) {
        validations.validationsOnSave(model, validatorsOutOfCore).forEach(Validator::validate);
        return repository.save(model);
    }

    @Override
    public Street update(Long id, Street model, List<Validator> validatorsOutOfCore) {
        validations.validationsOnSave(model, validatorsOutOfCore).forEach(Validator::validate);
        Street saved = findById(id);
        return repository.update(model, saved);
    }

    @Override
    public void delete(Long id, List<Validator> validatorsOutOfCore) {
        validations.validationsOnDelete(validatorsOutOfCore).forEach(Validator::validate);
        Street saved = findById(id);
        repository.delete(saved);
    }

}
