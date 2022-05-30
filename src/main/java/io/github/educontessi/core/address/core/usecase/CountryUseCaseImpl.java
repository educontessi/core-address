package io.github.educontessi.core.address.core.usecase;

import io.github.educontessi.core.address.core.exception.EntityNotFoundException;
import io.github.educontessi.core.address.core.filter.CountryFilter;
import io.github.educontessi.core.address.core.model.Country;
import io.github.educontessi.core.address.core.usecase.validation.Validator;
import io.github.educontessi.core.address.ports.in.CountryUseCasePort;
import io.github.educontessi.core.address.ports.out.CountryRepositoryPort;

import java.util.List;
import java.util.Optional;

public class CountryUseCaseImpl implements CountryUseCasePort {

    private final CountryRepositoryPort repository;

    public CountryUseCaseImpl(CountryRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public List<Country> findAll() {
        return repository.findAll();
    }

    @Override
    public Object search(CountryFilter filter, Object pageable) {
        return repository.search(filter, pageable);
    }

    @Override
    public Country findById(Long id) {
        Optional<Country> optionalSaved = repository.findById(id);
        return optionalSaved.orElseThrow(() -> new EntityNotFoundException(id));
    }

    @Override
    public Country save(Country model, List<Validator> validators) {
        validators.forEach(Validator::validate);
        return repository.save(model);
    }

    @Override
    public Country update(Long id, Country model, List<Validator> validators) {
        validators.forEach(Validator::validate);
        Country saved = findById(id);
        return repository.update(model, saved);
    }

    @Override
    public void delete(Long id, List<Validator> validators) {
        validators.forEach(Validator::validate);
        Country saved = findById(id);
        repository.delete(saved);
    }

}
