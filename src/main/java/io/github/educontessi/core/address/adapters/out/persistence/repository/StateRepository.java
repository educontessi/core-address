package io.github.educontessi.core.address.adapters.out.persistence.repository;

import io.github.educontessi.core.address.adapters.out.persistence.entity.StateEntity;
import io.github.educontessi.core.address.adapters.out.persistence.repository.customquery.StateRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StateRepository extends JpaRepository<StateEntity, Long>, StateRepositoryQuery {

    List<StateEntity> findAllByCountryId(Long countryId);

    Optional<StateEntity> findByUf(String uf);
}
