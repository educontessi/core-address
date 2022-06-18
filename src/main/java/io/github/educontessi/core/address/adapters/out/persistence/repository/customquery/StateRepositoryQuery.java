package io.github.educontessi.core.address.adapters.out.persistence.repository.customquery;

import io.github.educontessi.core.address.adapters.out.persistence.entity.StateEntity;
import io.github.educontessi.core.address.core.filter.StateFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StateRepositoryQuery {

    Page<StateEntity> search(StateFilter filter, Pageable pageable);

}
