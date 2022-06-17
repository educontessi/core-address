package io.github.educontessi.core.address.adapters.out.persistence.service;

import io.github.educontessi.core.address.adapters.out.persistence.dataconverter.CountryOutDataconverter;
import io.github.educontessi.core.address.adapters.out.persistence.entity.CountryEntity;
import io.github.educontessi.core.address.adapters.out.persistence.repository.CountryRepository;
import io.github.educontessi.core.address.core.config.GlobalParameters;
import io.github.educontessi.core.address.core.exception.EntityInUseException;
import io.github.educontessi.core.address.core.exception.EntityNotFoundException;
import io.github.educontessi.core.address.core.filter.CountryFilter;
import io.github.educontessi.core.address.core.model.Country;
import io.github.educontessi.core.address.ports.out.CountryRepositoryPort;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryService implements CountryRepositoryPort {

    private final CountryRepository repository;
    private final CountryOutDataconverter mapper;

    public CountryService(CountryRepository repository, CountryOutDataconverter mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<Country> findAll() {
        List<CountryEntity> list = repository.findAll();
        return list.stream().map(mapper::entityToModel).toList();
    }

    @Override
    public Page<Country> search(CountryFilter filter, Object pageable) {
        Page<CountryEntity> list = repository.search(filter, (Pageable) pageable);
        return new PageImpl<>(
                list.getContent().stream().map(mapper::entityToModel).toList(),
                list.getPageable(), list.getTotalElements());
    }

    @Override
    public Optional<Country> findById(Long id) {
        Optional<CountryEntity> optionalSaved = repository.findById(id);
        return optionalSaved.map(mapper::entityToModel);
    }

    @Override
    public Country save(Country model) {
        CountryEntity entity = new CountryEntity();
        mapper.modelToEntity(entity, model);
        repository.save(entity);
        return mapper.entityToModel(entity);
    }

    @Override
    public Country update(Country model, Country saved) {
        BeanUtils.copyProperties(model, saved, model.getIgnoreProperties());
        return save(saved);
    }

    @Override
    public void delete(Country saved) {
        CountryEntity entity = new CountryEntity();
        mapper.modelToEntity(entity, saved);
        if (GlobalParameters.EXCLUDE_DEFINITIVE) {
            definitiveDelete(entity);
        } else {
            paranoidDelete(entity);
        }
    }

    protected void definitiveDelete(CountryEntity saved) {
        try {
            repository.delete(saved);
            repository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(saved.getId());

        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(saved.getId());
        }
    }

    protected void paranoidDelete(CountryEntity saved) {
        saved.setDeleted(true);
        repository.saveAndFlush(saved);
    }

}
