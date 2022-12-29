package io.github.educontessi.core.address.adapters.out.persistence.jpa.repository.customquery;

import io.github.educontessi.core.address.adapters.out.persistence.jpa.entity.NeighborhoodEntity;
import io.github.educontessi.core.address.core.filter.NeighborhoodFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NeighborhoodRepositoryQuery {

    Page<NeighborhoodEntity> search(NeighborhoodFilter filter, Pageable pageable);

}
