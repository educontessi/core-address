package io.github.educontessi.core.address.core.ports.out;

import io.github.educontessi.core.address.core.model.ZipCodeSearch;

public interface ZipCodeSearchPort {

    ZipCodeSearch search(String zipCode);

}
