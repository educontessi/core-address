package io.github.educontessi.core.address.adapters.out.persistence.service;

import io.github.educontessi.core.address.adapters.out.persistence.dataconverter.NeighborhoodOutDataconverter;
import io.github.educontessi.core.address.adapters.out.persistence.entity.NeighborhoodEntity;
import io.github.educontessi.core.address.adapters.out.persistence.repository.NeighborhoodRepository;
import io.github.educontessi.core.address.core.config.GlobalParameters;
import io.github.educontessi.core.address.core.exception.EntityInUseException;
import io.github.educontessi.core.address.core.exception.EntityNotFoundException;
import io.github.educontessi.core.address.core.filter.NeighborhoodFilter;
import io.github.educontessi.core.address.core.model.Neighborhood;
import io.github.educontessi.core.address.core.ports.out.NeighborhoodRepositoryPort;
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
public class NeighborhoodService implements NeighborhoodRepositoryPort {

    private final NeighborhoodRepository repository;
    private final NeighborhoodOutDataconverter mapper;

    public NeighborhoodService(NeighborhoodRepository repository, NeighborhoodOutDataconverter mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<Neighborhood> findAll(String expand) {
        List<NeighborhoodEntity> list = repository.findAll();
        return list.stream().map(e -> mapper.entityToModel(e, expand)).toList();
    }

    @Override
    public Page<Neighborhood> search(NeighborhoodFilter filter, Object pageable, String expand) {
        Page<NeighborhoodEntity> list = repository.search(filter, (Pageable) pageable);
        return new PageImpl<>(
                list.getContent().stream().map(e -> mapper.entityToModel(e, expand)).toList(),
                list.getPageable(), list.getTotalElements());
    }

    @Override
    public Optional<Neighborhood> findById(Long id, String expand) {
        Optional<NeighborhoodEntity> optionalSaved = repository.findById(id);
        return optionalSaved.map(e -> mapper.entityToModel(e, expand));
    }

    @Override
    public List<Neighborhood> findAllByCityId(Long cityId, String expand) {
        List<NeighborhoodEntity> list = repository.findAllByCityId(cityId);
        return list.stream().map(e -> mapper.entityToModel(e, expand)).toList();
    }

    @Override
    public Neighborhood save(Neighborhood model) {
        NeighborhoodEntity entity = new NeighborhoodEntity();
        mapper.modelToEntity(entity, model);
        repository.save(entity);
        return mapper.entityToModel(entity);
    }

    @Override
    public Neighborhood update(Neighborhood model, Neighborhood saved) {
        BeanUtils.copyProperties(model, saved, model.getIgnoreProperties());
        return save(saved);
    }

    @Override
    public void delete(Neighborhood saved) {
        NeighborhoodEntity entity = new NeighborhoodEntity();
        mapper.modelToEntity(entity, saved);
        if (GlobalParameters.EXCLUDE_DEFINITIVE) {
            definitiveDelete(entity);
        } else {
            paranoidDelete(entity);
        }
    }

    @Override
    public Optional<Neighborhood> findByNameAndCityId(String name, Long cityId) {
        Optional<NeighborhoodEntity> optionalSaved = repository.findByNameAndCityId(name, cityId);
        return optionalSaved.map(e -> mapper.entityToModel(e, null));
    }

    protected void definitiveDelete(NeighborhoodEntity saved) {
        try {
            repository.delete(saved);
            repository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(saved.getId());

        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(saved.getId());
        }
    }

    protected void paranoidDelete(NeighborhoodEntity saved) {
        saved.setDeleted(true);
        repository.saveAndFlush(saved);
    }

}
