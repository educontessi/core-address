package io.github.educontessi.core.address.adapters.out.persistence.service;

import io.github.educontessi.core.address.adapters.out.persistence.dataconverter.StateOutDataconverter;
import io.github.educontessi.core.address.adapters.out.persistence.entity.StateEntity;
import io.github.educontessi.core.address.adapters.out.persistence.repository.StateRepository;
import io.github.educontessi.core.address.core.config.GlobalParameters;
import io.github.educontessi.core.address.core.exception.EntityInUseException;
import io.github.educontessi.core.address.core.exception.EntityNotFoundException;
import io.github.educontessi.core.address.core.filter.StateFilter;
import io.github.educontessi.core.address.core.model.State;
import io.github.educontessi.core.address.ports.out.StateRepositoryPort;
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
public class StateService implements StateRepositoryPort {

    private final StateRepository repository;
    private final StateOutDataconverter mapper;

    public StateService(StateRepository repository, StateOutDataconverter mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<State> findAll(String expand) {
        List<StateEntity> list = repository.findAll();
        return list.stream().map(e -> mapper.entityToModel(e, expand)).toList();
    }

    @Override
    public Page<State> search(StateFilter filter, Object pageable, String expand) {
        Page<StateEntity> list = repository.search(filter, (Pageable) pageable);
        return new PageImpl<>(
                list.getContent().stream().map(e -> mapper.entityToModel(e, expand)).toList(),
                list.getPageable(), list.getTotalElements());
    }

    @Override
    public Optional<State> findById(Long id, String expand) {
        Optional<StateEntity> optionalSaved = repository.findById(id);
        return optionalSaved.map(e -> mapper.entityToModel(e, expand));
    }

    @Override
    public List<State> findAllByCountryId(Long countryId, String expand) {
        List<StateEntity> list = repository.findAllByCountryId(countryId);
        return list.stream().map(e -> mapper.entityToModel(e, expand)).toList();
    }

    @Override
    public Optional<State> findByUf(String uf, String expand) {
        Optional<StateEntity> optionalSaved = repository.findByUf(uf);
        return optionalSaved.map(e -> mapper.entityToModel(e, expand));
    }

    @Override
    public State save(State model) {
        StateEntity entity = new StateEntity();
        mapper.modelToEntity(entity, model);
        repository.save(entity);
        return mapper.entityToModel(entity);
    }

    @Override
    public State update(State model, State saved) {
        BeanUtils.copyProperties(model, saved, model.getIgnoreProperties());
        return save(saved);
    }

    @Override
    public void delete(State saved) {
        StateEntity entity = new StateEntity();
        mapper.modelToEntity(entity, saved);
        if (GlobalParameters.EXCLUDE_DEFINITIVE) {
            definitiveDelete(entity);
        } else {
            paranoidDelete(entity);
        }
    }

    protected void definitiveDelete(StateEntity saved) {
        try {
            repository.delete(saved);
            repository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(saved.getId());

        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(saved.getId());
        }
    }

    protected void paranoidDelete(StateEntity saved) {
        saved.setDeleted(true);
        repository.saveAndFlush(saved);
    }

}
