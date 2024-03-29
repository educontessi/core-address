package io.github.educontessi.core.address.adapters.out.ports.impl;

import io.github.educontessi.core.address.adapters.out.persistence.jpa.dataconverter.CityOutDataconverter;
import io.github.educontessi.core.address.adapters.out.persistence.jpa.entity.CityEntity;
import io.github.educontessi.core.address.adapters.out.persistence.jpa.repository.CityRepository;
import io.github.educontessi.core.address.core.config.GlobalParameters;
import io.github.educontessi.core.address.core.exception.EntityInUseException;
import io.github.educontessi.core.address.core.exception.EntityNotFoundException;
import io.github.educontessi.core.address.core.filter.CityFilter;
import io.github.educontessi.core.address.core.model.City;
import io.github.educontessi.core.address.core.ports.out.CityAdapterPort;
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
public class CityAdapterPortImpl implements CityAdapterPort {

    private final CityRepository repository;
    private final CityOutDataconverter mapper;

    public CityAdapterPortImpl(CityRepository repository, CityOutDataconverter mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Page<City> search(CityFilter filter, Object pageable, String expand) {
        Page<CityEntity> list = repository.search(filter, (Pageable) pageable);
        return new PageImpl<>(
                list.getContent().stream().map(e -> mapper.entityToModel(e, expand)).toList(),
                list.getPageable(), list.getTotalElements());
    }

    @Override
    @Cacheable(value = "core-address-city")
    public Optional<City> findById(Long id, String expand) {
        Optional<CityEntity> optionalSaved = repository.findById(id);
        return optionalSaved.map(e -> mapper.entityToModel(e, expand));
    }

    @Override
    public List<City> findAllByStateId(Long stateId, String expand) {
        List<CityEntity> list = repository.findAllByStateId(stateId);
        return list.stream().map(e -> mapper.entityToModel(e, expand)).toList();
    }

    @Override
    @Cacheable(value = "core-address-city")
    public Optional<City> findByIbge(Integer ibge, String expand) {
        Optional<CityEntity> optionalSaved = repository.findByIbge(ibge);
        return optionalSaved.map(e -> mapper.entityToModel(e, expand));
    }

    @Override
    @CacheEvict(value = "core-address-city", key = "#p0.id", condition = "#p0.id != null")
    public City save(City model) {
        CityEntity entity = new CityEntity();
        mapper.modelToEntity(entity, model);
        repository.save(entity);
        return mapper.entityToModel(entity);
    }

    @Override
    public City update(City model, City saved) {
        BeanUtils.copyProperties(model, saved, model.getIgnoreProperties());
        return save(saved);
    }

    @Override
    @CacheEvict(value = "core-address-city", key = "#p0.id")
    public void delete(City saved) {
        CityEntity entity = new CityEntity();
        mapper.modelToEntity(entity, saved);
        if (GlobalParameters.EXCLUDE_DEFINITIVE) {
            definitiveDelete(entity);
        } else {
            paranoidDelete(entity);
        }
    }

    protected void definitiveDelete(CityEntity saved) {
        try {
            repository.delete(saved);
            repository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(saved.getId());

        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(saved.getId());
        }
    }

    protected void paranoidDelete(CityEntity saved) {
        saved.setDeleted(true);
        repository.saveAndFlush(saved);
    }

}
