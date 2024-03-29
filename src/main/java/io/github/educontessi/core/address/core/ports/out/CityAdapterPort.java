package io.github.educontessi.core.address.core.ports.out;

import io.github.educontessi.core.address.core.exception.EntityInUseException;
import io.github.educontessi.core.address.core.exception.EntityNotFoundException;
import io.github.educontessi.core.address.core.filter.CityFilter;
import io.github.educontessi.core.address.core.model.City;

import java.util.List;
import java.util.Optional;

public interface CityAdapterPort {

    Object search(CityFilter filter, Object pageable, String expand);

    Optional<City> findById(Long id, String expand);

    List<City> findAllByStateId(Long stateId, String expand);

    Optional<City> findByIbge(Integer ibge, String expand);

    City save(City model);

    City update(City model, City saved);

    void delete(City saved) throws EntityNotFoundException, EntityInUseException;

}
