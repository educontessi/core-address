package io.github.educontessi.core.address.core.ports.in;

import io.github.educontessi.core.address.core.filter.CountryFilter;
import io.github.educontessi.core.address.core.model.Country;
import io.github.educontessi.core.address.core.validation.Validator;

import java.util.List;

public interface CountryUseCasePort {

    List<Country> findAll();

    Object search(CountryFilter filter, Object pageable);

    Country findById(Long id);

    Country save(Country model, List<Validator> validatorsOutOfCore);

    Country update(Long id, Country model, List<Validator> validatorsOutOfCore);

    void delete(Long id, List<Validator> validatorsOutOfCore);

}
