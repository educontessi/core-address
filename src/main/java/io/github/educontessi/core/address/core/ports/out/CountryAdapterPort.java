package io.github.educontessi.core.address.core.ports.out;

import io.github.educontessi.core.address.core.exception.EntityInUseException;
import io.github.educontessi.core.address.core.exception.EntityNotFoundException;
import io.github.educontessi.core.address.core.filter.CountryFilter;
import io.github.educontessi.core.address.core.model.Country;

import java.util.List;
import java.util.Optional;

public interface CountryAdapterPort {

    List<Country> findAll();

    Object search(CountryFilter filter, Object pageable);

    Optional<Country> findById(Long id);

    Country save(Country model);

    Country update(Country model, Country saved);

    void delete(Country saved) throws EntityNotFoundException, EntityInUseException;

}
