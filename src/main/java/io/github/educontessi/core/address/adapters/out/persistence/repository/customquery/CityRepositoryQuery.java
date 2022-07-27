package io.github.educontessi.core.address.adapters.out.persistence.repository.customquery;

import io.github.educontessi.core.address.adapters.out.persistence.entity.CityEntity;
import io.github.educontessi.core.address.core.filter.CityFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CityRepositoryQuery {

    Page<CityEntity> search(CityFilter filter, Pageable pageable);

}
