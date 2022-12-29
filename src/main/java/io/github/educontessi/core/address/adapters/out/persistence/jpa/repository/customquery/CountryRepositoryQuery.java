package io.github.educontessi.core.address.adapters.out.persistence.jpa.repository.customquery;

import io.github.educontessi.core.address.adapters.out.persistence.jpa.entity.CountryEntity;
import io.github.educontessi.core.address.core.filter.CountryFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CountryRepositoryQuery {

    Page<CountryEntity> search(CountryFilter filter, Pageable pageable);

}
