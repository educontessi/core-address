package io.github.educontessi.core.address.adapters.out.persistence.repository;

import io.github.educontessi.core.address.adapters.out.persistence.entity.NeighborhoodEntity;
import io.github.educontessi.core.address.adapters.out.persistence.repository.customquery.NeighborhoodRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NeighborhoodRepository extends JpaRepository<NeighborhoodEntity, Long>, NeighborhoodRepositoryQuery {

    Optional<NeighborhoodEntity> findByNameAndCityId(String name, Long cityId);

    List<NeighborhoodEntity> findAllByCityId(Long cityId);

}
