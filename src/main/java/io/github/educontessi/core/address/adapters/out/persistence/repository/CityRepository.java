package io.github.educontessi.core.address.adapters.out.persistence.repository;

import io.github.educontessi.core.address.adapters.out.persistence.entity.CityEntity;
import io.github.educontessi.core.address.adapters.out.persistence.repository.customquery.CityRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<CityEntity, Long>, CityRepositoryQuery {

    List<CityEntity> findAllByStateId(Long stateId);

    Optional<CityEntity> findByIbge(Integer ibge);
}
