package io.github.educontessi.core.address.adapters.out.ports.impl;

import io.github.educontessi.core.address.adapters.out.persistence.jpa.dataconverter.AddressOutDataconverter;
import io.github.educontessi.core.address.adapters.out.persistence.jpa.entity.AddressEntity;
import io.github.educontessi.core.address.adapters.out.persistence.jpa.repository.AddressRepository;
import io.github.educontessi.core.address.core.config.GlobalParameters;
import io.github.educontessi.core.address.core.exception.EntityInUseException;
import io.github.educontessi.core.address.core.exception.EntityNotFoundException;
import io.github.educontessi.core.address.core.model.Address;
import io.github.educontessi.core.address.core.ports.out.AddressAdapterPort;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressAdapterPortImpl implements AddressAdapterPort {

    private final AddressRepository repository;
    private final AddressOutDataconverter mapper;

    public AddressAdapterPortImpl(AddressRepository repository, AddressOutDataconverter mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    @Cacheable(value = "core-address")
    public List<Address> findAllByIntegrationId(String integrationId) {
        List<AddressEntity> list = repository.findAllByIntegrationId(integrationId);
        return list.stream().map(mapper::entityToModel).toList();
    }

    @Override
    @Cacheable(value = "core-address")
    public Optional<Address> findById(Long id, String integrationId) {
        Optional<AddressEntity> optionalSaved = repository.findByIdAndIntegrationId(id, integrationId);
        return optionalSaved.map(mapper::entityToModel);
    }

    @Override
    @Cacheable(value = "core-address-default")
    public Optional<Address> findDefaultAddress(String integrationId) {
        Optional<AddressEntity> optionalSaved = repository.findByIntegrationIdAndDefaultAddressIsTrue(integrationId);
        return optionalSaved.map(mapper::entityToModel);
    }

    @Override
    @CacheEvict(cacheNames = {"core-address", "core-address-default"}, allEntries = true)
    public Address save(Address model) {
        AddressEntity entity = new AddressEntity();
        mapper.modelToEntity(entity, model);
        entity = repository.save(entity);
        return mapper.entityToModel(entity);
    }

    @Override
    public Address update(Address model, Address saved) {
        BeanUtils.copyProperties(model, saved, model.getIgnoreProperties());
        return save(saved);
    }

    @Override
    @CacheEvict(cacheNames = {"core-address", "core-address-default"}, allEntries = true)
    public void delete(Address saved) {
        AddressEntity entity = new AddressEntity();
        mapper.modelToEntity(entity, saved);
        if (GlobalParameters.EXCLUDE_DEFINITIVE) {
            definitiveDelete(entity);
        } else {
            paranoidDelete(entity);
        }
    }

    protected void definitiveDelete(AddressEntity saved) {
        try {
            repository.delete(saved);
            repository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(saved.getId());

        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(saved.getId());
        }
    }

    protected void paranoidDelete(AddressEntity saved) {
        saved.setDeleted(true);
        repository.saveAndFlush(saved);
    }

}
