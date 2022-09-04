package io.github.educontessi.core.address.core.ports.in;

import io.github.educontessi.core.address.core.model.ZipCodeSearch;

public interface ZipCodeSearchUseCasePort {

    ZipCodeSearch execute(String zipCode);

}
