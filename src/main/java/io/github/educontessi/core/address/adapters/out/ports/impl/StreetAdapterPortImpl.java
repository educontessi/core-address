package io.github.educontessi.core.address.adapters.out.ports.impl;

import io.github.educontessi.core.address.adapters.out.persistence.jpa.dataconverter.StreetOutDataconverter;
import io.github.educontessi.core.address.adapters.out.persistence.jpa.entity.StreetEntity;
import io.github.educontessi.core.address.adapters.out.persistence.jpa.repository.StreetRepository;
import io.github.educontessi.core.address.core.config.GlobalParameters;
import io.github.educontessi.core.address.core.exception.EntityInUseException;
import io.github.educontessi.core.address.core.exception.EntityNotFoundException;
import io.github.educontessi.core.address.core.filter.StreetFilter;
import io.github.educontessi.core.address.core.model.Street;
import io.github.educontessi.core.address.core.ports.out.StreetAdapterPort;
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
public class StreetAdapterPortImpl implements StreetAdapterPort {

    private final StreetRepository repository;
    private final StreetOutDataconverter mapper;

    public StreetAdapterPortImpl(StreetRepository repository, StreetOutDataconverter mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Page<Street> search(StreetFilter filter, Object pageable, String expand) {
        Page<StreetEntity> list = repository.search(filter, (Pageable) pageable);
        return new PageImpl<>(
                list.getContent().stream().map(e -> mapper.entityToModel(e, expand)).toList(),
                list.getPageable(), list.getTotalElements());
    }

    @Override
    @Cacheable(value = "core-address-street")
    public Optional<Street> findById(Long id, String expand) {
        Optional<StreetEntity> optionalSaved = repository.findById(id);
        return optionalSaved.map(e -> mapper.entityToModel(e, expand));
    }

    @Override
    public List<Street> findAllByCityId(Long cityId, String expand) {
        List<StreetEntity> list = repository.findAllByCityId(cityId);
        return list.stream().map(e -> mapper.entityToModel(e, expand)).toList();
    }

    @Override
    @CacheEvict(value = "core-address-street", key = "#p0.id", condition = "#p0.id != null")
    public Street save(Street model) {
        StreetEntity entity = new StreetEntity();
        mapper.modelToEntity(entity, model);
        repository.save(entity);
        return mapper.entityToModel(entity);
    }

    @Override
    public Street update(Street model, Street saved) {
        BeanUtils.copyProperties(model, saved, model.getIgnoreProperties());
        return save(saved);
    }

    @Override
    @CacheEvict(value = "core-address-street", key = "#p0.id")
    public void delete(Street saved) {
        StreetEntity entity = new StreetEntity();
        mapper.modelToEntity(entity, saved);
        if (GlobalParameters.EXCLUDE_DEFINITIVE) {
            definitiveDelete(entity);
        } else {
            paranoidDelete(entity);
        }
    }

    @Override
    @Cacheable(value = "core-address-street", unless="#result == null")
    public Optional<Street> findByNameAndCityId(String name, Long cityId) {
        Optional<StreetEntity> optionalSaved = repository.findByNameAndCityId(name, cityId);
        return optionalSaved.map(e -> mapper.entityToModel(e, null));
    }

    protected void definitiveDelete(StreetEntity saved) {
        try {
            repository.delete(saved);
            repository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(saved.getId());

        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(saved.getId());
        }
    }

    protected void paranoidDelete(StreetEntity saved) {
        saved.setDeleted(true);
        repository.saveAndFlush(saved);
    }

}
