package io.github.educontessi.core.address.core.ports.out;

import io.github.educontessi.core.address.core.exception.EntityInUseException;
import io.github.educontessi.core.address.core.exception.EntityNotFoundException;
import io.github.educontessi.core.address.core.filter.StreetFilter;
import io.github.educontessi.core.address.core.model.Street;

import java.util.List;
import java.util.Optional;

public interface StreetRepositoryPort {

    Object search(StreetFilter filter, Object pageable, String expand);

    Optional<Street> findById(Long id, String expand);

    List<Street> findAllByCityId(Long cityId, String expand);

    Street save(Street model);

    Street update(Street model, Street saved);

    void delete(Street saved) throws EntityNotFoundException, EntityInUseException;

    Optional<Street> findByNameAndCityId(String name, Long cityId);
}
