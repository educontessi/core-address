package io.github.educontessi.core.address.core.ports.out;

import io.github.educontessi.core.address.core.exception.EntityInUseException;
import io.github.educontessi.core.address.core.exception.EntityNotFoundException;
import io.github.educontessi.core.address.core.model.Address;

import java.util.List;
import java.util.Optional;

public interface AddressRepositoryPort {

    List<Address> findAllByIntegrationId(String integrationId);

    Optional<Address> findById(Long id, String integrationId);

    Optional<Address> findDefaultAddress(String integrationId);

    Address save(Address model);

    Address update(Address model, Address saved);

    void delete(Address saved) throws EntityNotFoundException, EntityInUseException;

}
