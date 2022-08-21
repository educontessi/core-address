package io.github.educontessi.core.address.core.usecase;

import io.github.educontessi.core.address.core.exception.EntityNotFoundException;
import io.github.educontessi.core.address.core.model.Address;
import io.github.educontessi.core.address.core.ports.in.AddressUseCasePort;
import io.github.educontessi.core.address.core.ports.out.AddressRepositoryPort;
import io.github.educontessi.core.address.core.validation.AddressValidations;
import io.github.educontessi.core.address.core.validation.Validator;

import java.util.List;
import java.util.Optional;

public class AddressUseCaseImpl implements AddressUseCasePort {

    private final AddressRepositoryPort repository;
    private final AddressValidations validations;

    public AddressUseCaseImpl(AddressRepositoryPort repository, AddressValidations validations) {
        this.repository = repository;
        this.validations = validations;
    }

    @Override
    public List<Address> findAllByIntegrationId(String integrationId) {
        return repository.findAllByIntegrationId(integrationId);
    }

    @Override
    public Address findById(Long id, String integrationId) {
        Optional<Address> optionalSaved = repository.findById(id, integrationId);
        return optionalSaved.orElseThrow(() -> new EntityNotFoundException(id));
    }

    @Override
    public Address findDefaultAddress(String integrationId) {
        Optional<Address> optionalSaved = repository.findDefaultAddress(integrationId);
        return optionalSaved.orElseThrow(() -> new EntityNotFoundException(String.format("There is no default address for the integrationId %s", integrationId)));
    }

    @Override
    public Address save(Address model, List<Validator> validatorsOutOfCore) {
        validations.validationsOnSave(model, validatorsOutOfCore).forEach(Validator::validate);
        return repository.save(model);
    }

    @Override
    public Address update(Long id, Address model, List<Validator> validatorsOutOfCore) {
        validations.validationsOnSave(model, validatorsOutOfCore).forEach(Validator::validate);
        Address saved = findById(id, model.getIntegrationId());
        return repository.update(model, saved);
    }

    @Override
    public void delete(Long id, String integrationId, List<Validator> validatorsOutOfCore) {
        validations.validationsOnDelete(validatorsOutOfCore).forEach(Validator::validate);
        Address saved = findById(id, integrationId);
        repository.delete(saved);
    }

}
