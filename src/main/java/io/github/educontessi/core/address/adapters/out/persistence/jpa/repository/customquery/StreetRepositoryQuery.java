package io.github.educontessi.core.address.adapters.out.persistence.jpa.repository.customquery;

import io.github.educontessi.core.address.adapters.out.persistence.jpa.entity.StreetEntity;
import io.github.educontessi.core.address.core.filter.StreetFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StreetRepositoryQuery {

    Page<StreetEntity> search(StreetFilter filter, Pageable pageable);

}
