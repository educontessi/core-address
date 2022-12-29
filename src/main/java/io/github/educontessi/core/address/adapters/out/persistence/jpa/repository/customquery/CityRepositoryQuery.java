package io.github.educontessi.core.address.adapters.out.persistence.jpa.repository.customquery;

import io.github.educontessi.core.address.adapters.out.persistence.jpa.entity.CityEntity;
import io.github.educontessi.core.address.core.filter.CityFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CityRepositoryQuery {

    Page<CityEntity> search(CityFilter filter, Pageable pageable);

}
