package io.github.educontessi.core.address.core.ports.in;

import io.github.educontessi.core.address.core.filter.NeighborhoodFilter;
import io.github.educontessi.core.address.core.model.Neighborhood;
import io.github.educontessi.core.address.core.validation.Validator;

import java.util.List;

public interface NeighborhoodUseCasePort {

    List<Neighborhood> findAll(String expand);

    Object search(NeighborhoodFilter filter, Object pageable, String expand);

    Neighborhood findById(Long id, String expand);

    List<Neighborhood> findAllByCityId(Long cityId, String expand);

    Neighborhood save(Neighborhood model, List<Validator> validatorsOutOfCore);

    Neighborhood update(Long id, Neighborhood model, List<Validator> validatorsOutOfCore);

    void delete(Long id, List<Validator> validatorsOutOfCore);

    Neighborhood findByNameAndCityId(String bairro, Long id);
}
