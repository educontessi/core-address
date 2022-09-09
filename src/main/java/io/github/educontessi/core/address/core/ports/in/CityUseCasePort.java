package io.github.educontessi.core.address.core.ports.in;

import io.github.educontessi.core.address.core.filter.CityFilter;
import io.github.educontessi.core.address.core.model.City;
import io.github.educontessi.core.address.core.validation.Validator;

import java.util.List;

public interface CityUseCasePort {

    Object search(CityFilter filter, Object pageable, String expand);

    City findById(Long id, String expand);

    List<City> findAllByStateId(Long stateId, String expand);

    City findByIbge(Integer ibge, String expand);

    City save(City model, List<Validator> validatorsOutOfCore);

    City update(Long id, City model, List<Validator> validatorsOutOfCore);

    void delete(Long id, List<Validator> validatorsOutOfCore);

}
