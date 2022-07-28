package io.github.educontessi.core.address.core.ports.out;

import io.github.educontessi.core.address.core.exception.EntityInUseException;
import io.github.educontessi.core.address.core.exception.EntityNotFoundException;
import io.github.educontessi.core.address.core.filter.NeighborhoodFilter;
import io.github.educontessi.core.address.core.model.Neighborhood;

import java.util.List;
import java.util.Optional;

public interface NeighborhoodRepositoryPort {

    List<Neighborhood> findAll(String expand);

    Object search(NeighborhoodFilter filter, Object pageable, String expand);

    Optional<Neighborhood> findById(Long id, String expand);

    List<Neighborhood> findAllByCityId(Long cityId, String expand);

    Neighborhood save(Neighborhood model);

    Neighborhood update(Neighborhood model, Neighborhood saved);

    void delete(Neighborhood saved) throws EntityNotFoundException, EntityInUseException;

    Optional<Neighborhood> findByNameAndCityId(String name, Long cityId);
}
