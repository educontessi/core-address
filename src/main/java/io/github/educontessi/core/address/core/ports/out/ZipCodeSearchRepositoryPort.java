package io.github.educontessi.core.address.core.ports.out;

import io.github.educontessi.core.address.core.model.ZipCodeSearch;

public interface ZipCodeSearchRepositoryPort {

    ZipCodeSearch search(String zipCode);

}
