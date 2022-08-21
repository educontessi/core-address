package io.github.educontessi.core.address.core.usecase;

import io.github.educontessi.core.address.core.model.ZipCodeSearch;
import io.github.educontessi.core.address.core.ports.in.ZipCodeSearchUseCasePort;
import io.github.educontessi.core.address.core.ports.out.ZipCodeSearchRepositoryPort;
import io.github.educontessi.core.address.core.validation.Validator;
import io.github.educontessi.core.address.core.validation.ZipCodeSearchValidations;

public class ZipCodeSearchUseCaseImpl implements ZipCodeSearchUseCasePort {

    private final ZipCodeSearchRepositoryPort repository;
    private final ZipCodeSearchValidations validations;

    public ZipCodeSearchUseCaseImpl(ZipCodeSearchRepositoryPort repository, ZipCodeSearchValidations validations) {
        this.repository = repository;
        this.validations = validations;
    }

    @Override
    public ZipCodeSearch search(String zipCode) {
        validations.validationsOnSearch(zipCode).forEach(Validator::validate);
        return repository.search(zipCode);
    }

}
