package io.github.educontessi.core.address.adapters.out.ports.impl;

import io.github.educontessi.core.address.adapters.out.persistence.jpa.dataconverter.CountryOutDataconverter;
import io.github.educontessi.core.address.adapters.out.persistence.jpa.entity.CountryEntity;
import io.github.educontessi.core.address.adapters.out.persistence.jpa.repository.CountryRepository;
import io.github.educontessi.core.address.core.config.GlobalParameters;
import io.github.educontessi.core.address.core.exception.EntityInUseException;
import io.github.educontessi.core.address.core.exception.EntityNotFoundException;
import io.github.educontessi.core.address.core.filter.CountryFilter;
import io.github.educontessi.core.address.core.model.Country;
import io.github.educontessi.core.address.core.ports.out.CountryAdapterPort;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryAdapterPortImpl implements CountryAdapterPort {

    private final CountryRepository repository;
    private final CountryOutDataconverter mapper;

    public CountryAdapterPortImpl(CountryRepository repository, CountryOutDataconverter mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    @Cacheable(value = "core-address-country")
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
    @Cacheable(value = "core-address-country")
    public Optional<Country> findById(Long id) {
        Optional<CountryEntity> optionalSaved = repository.findById(id);
        return optionalSaved.map(mapper::entityToModel);
    }

    @Override
    @CacheEvict(value = "core-address-country", key = "#p0.id", condition = "#p0.id != null")
    public Country save(Country model) {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>"+ model.getId());
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
    @CacheEvict(value = "core-address-country", key = "#p0.id")
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
