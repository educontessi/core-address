package io.github.educontessi.core.address.core.usecase;

import io.github.educontessi.core.address.core.model.ZipCodeSearch;
import io.github.educontessi.core.address.core.ports.in.ZipCodeSearchUseCasePort;
import io.github.educontessi.core.address.core.ports.out.ZipCodeSearchAdapterPort;
import io.github.educontessi.core.address.core.validation.Validator;
import io.github.educontessi.core.address.core.validation.ZipCodeSearchValidations;

public class ZipCodeSearchUseCase implements ZipCodeSearchUseCasePort {

    private final ZipCodeSearchAdapterPort repository;
    private final ZipCodeSearchValidations validations;

    public ZipCodeSearchUseCase(ZipCodeSearchAdapterPort repository, ZipCodeSearchValidations validations) {
        this.repository = repository;
        this.validations = validations;
    }

    @Override
    public ZipCodeSearch execute(String zipCode) {
        validations.validationsOnSearch(zipCode).forEach(Validator::validate);
        return repository.search(zipCode);
    }

}
