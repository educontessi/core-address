package io.github.educontessi.core.address.core.ports.out;

import io.github.educontessi.core.address.core.model.ZipCodeSearch;

public interface ZipCodeSearchAdapterPort {

    ZipCodeSearch search(String zipCode);

}
