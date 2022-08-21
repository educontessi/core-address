package io.github.educontessi.core.address.core.ports.in;

import io.github.educontessi.core.address.core.model.Address;
import io.github.educontessi.core.address.core.validation.Validator;

import java.util.List;

public interface AddressUseCasePort {

    List<Address> findAllByIntegrationId(String integrationId);

    Address findById(Long id, String integrationId);

    Address findDefaultAddress(String integrationId);

    Address save(Address model, List<Validator> validatorsOutOfCore);

    Address update(Long id, Address model, List<Validator> validatorsOutOfCore);

    void delete(Long id, String integrationId, List<Validator> validatorsOutOfCore);

}
