package io.github.educontessi.core.address.adapters.out.persistence.repository.customquery;

import io.github.educontessi.core.address.adapters.out.persistence.entity.NeighborhoodEntity;
import io.github.educontessi.core.address.core.filter.NeighborhoodFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NeighborhoodRepositoryQuery {

    Page<NeighborhoodEntity> search(NeighborhoodFilter filter, Pageable pageable);

}
