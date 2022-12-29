package io.github.educontessi.core.address.adapters.out.persistence.jpa.repository;

import io.github.educontessi.core.address.adapters.out.persistence.jpa.entity.StateEntity;
import io.github.educontessi.core.address.adapters.out.persistence.jpa.repository.customquery.StateRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StateRepository extends JpaRepository<StateEntity, Long>, StateRepositoryQuery {

    List<StateEntity> findAllByCountryId(Long countryId);

    Optional<StateEntity> findByUf(String uf);
}
