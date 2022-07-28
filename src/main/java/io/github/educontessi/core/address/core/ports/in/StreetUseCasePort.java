package io.github.educontessi.core.address.core.ports.in;

import io.github.educontessi.core.address.core.filter.StreetFilter;
import io.github.educontessi.core.address.core.model.Street;
import io.github.educontessi.core.address.core.validation.Validator;

import java.util.List;

public interface StreetUseCasePort {

    List<Street> findAll(String expand);

    Object search(StreetFilter filter, Object pageable, String expand);

    Street findById(Long id, String expand);

    List<Street> findAllByCityId(Long cityId, String expand);

    Street save(Street model, List<Validator> validatorsOutOfCore);

    Street update(Long id, Street model, List<Validator> validatorsOutOfCore);

    void delete(Long id, List<Validator> validatorsOutOfCore);

}
