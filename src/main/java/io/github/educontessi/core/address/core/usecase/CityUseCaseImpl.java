package io.github.educontessi.core.address.core.usecase;

import io.github.educontessi.core.address.core.exception.EntityNotFoundException;
import io.github.educontessi.core.address.core.filter.CityFilter;
import io.github.educontessi.core.address.core.model.City;
import io.github.educontessi.core.address.core.validation.CityValidations;
import io.github.educontessi.core.address.core.validation.Validator;
import io.github.educontessi.core.address.core.ports.in.CityUseCasePort;
import io.github.educontessi.core.address.core.ports.out.CityRepositoryPort;

import java.util.List;
import java.util.Optional;

public class CityUseCaseImpl implements CityUseCasePort {

    private final CityRepositoryPort repository;
    private final CityValidations validations;

    public CityUseCaseImpl(CityRepositoryPort repository, CityValidations validations) {
        this.repository = repository;
        this.validations = validations;
    }

    @Override
    public List<City> findAll(String expand) {
        return repository.findAll(expand);
    }

    @Override
    public Object search(CityFilter filter, Object pageable, String expand) {
        return repository.search(filter, pageable, expand);
    }

    @Override
    public City findById(Long id, String expand) {
        Optional<City> optionalSaved = repository.findById(id, expand);
        return optionalSaved.orElseThrow(() -> new EntityNotFoundException(id));
    }

    public City findById(Long id) {
        return findById(id, null);
    }

    @Override
    public List<City> findAllByStateId(Long stateId, String expand) {
        return repository.findAllByStateId(stateId, expand);
    }

    @Override
    public City findByIbge(Integer ibge, String expand) {
        Optional<City> optionalSaved = repository.findByIbge(ibge, expand);
        return optionalSaved.orElseThrow(() -> new EntityNotFoundException(String.format("There is no record with IBGE %s", ibge)));
    }

    @Override
    public City save(City model, List<Validator> validatorsOutOfCore) {
        validations.validationsOnSave(model, validatorsOutOfCore).forEach(Validator::validate);
        return repository.save(model);
    }

    @Override
    public City update(Long id, City model, List<Validator> validatorsOutOfCore) {
        validations.validationsOnSave(model, validatorsOutOfCore).forEach(Validator::validate);
        City saved = findById(id);
        return repository.update(model, saved);
    }

    @Override
    public void delete(Long id, List<Validator> validatorsOutOfCore) {
        validations.validationsOnDelete(validatorsOutOfCore).forEach(Validator::validate);
        City saved = findById(id);
        repository.delete(saved);
    }

}
