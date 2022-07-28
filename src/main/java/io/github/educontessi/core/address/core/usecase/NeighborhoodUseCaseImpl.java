package io.github.educontessi.core.address.core.usecase;

import io.github.educontessi.core.address.core.exception.EntityNotFoundException;
import io.github.educontessi.core.address.core.filter.NeighborhoodFilter;
import io.github.educontessi.core.address.core.model.Neighborhood;
import io.github.educontessi.core.address.core.validation.NeighborhoodValidations;
import io.github.educontessi.core.address.core.validation.Validator;
import io.github.educontessi.core.address.core.ports.in.NeighborhoodUseCasePort;
import io.github.educontessi.core.address.core.ports.out.NeighborhoodRepositoryPort;

import java.util.List;
import java.util.Optional;

public class NeighborhoodUseCaseImpl implements NeighborhoodUseCasePort {

    private final NeighborhoodRepositoryPort repository;
    private final NeighborhoodValidations validations;

    public NeighborhoodUseCaseImpl(NeighborhoodRepositoryPort repository, NeighborhoodValidations validations) {
        this.repository = repository;
        this.validations = validations;
    }

    @Override
    public List<Neighborhood> findAll(String expand) {
        return repository.findAll(expand);
    }

    @Override
    public Object search(NeighborhoodFilter filter, Object pageable, String expand) {
        return repository.search(filter, pageable, expand);
    }

    @Override
    public Neighborhood findById(Long id, String expand) {
        Optional<Neighborhood> optionalSaved = repository.findById(id, expand);
        return optionalSaved.orElseThrow(() -> new EntityNotFoundException(id));
    }

    public Neighborhood findById(Long id) {
        return findById(id, null);
    }

    @Override
    public List<Neighborhood> findAllByCityId(Long cityId, String expand) {
        return repository.findAllByCityId(cityId, expand);
    }

    @Override
    public Neighborhood save(Neighborhood model, List<Validator> validatorsOutOfCore) {
        validations.validationsOnSave(model, validatorsOutOfCore).forEach(Validator::validate);
        return repository.save(model);
    }

    @Override
    public Neighborhood update(Long id, Neighborhood model, List<Validator> validatorsOutOfCore) {
        validations.validationsOnSave(model, validatorsOutOfCore).forEach(Validator::validate);
        Neighborhood saved = findById(id);
        return repository.update(model, saved);
    }

    @Override
    public void delete(Long id, List<Validator> validatorsOutOfCore) {
        validations.validationsOnDelete(validatorsOutOfCore).forEach(Validator::validate);
        Neighborhood saved = findById(id);
        repository.delete(saved);
    }

    @Override
    public Neighborhood findByNameAndCityId(String name, Long cityId) {
        return repository.findByNameAndCityId(name, cityId).orElse(null);
    }

}
