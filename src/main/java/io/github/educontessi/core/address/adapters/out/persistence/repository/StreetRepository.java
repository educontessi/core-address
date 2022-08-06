package io.github.educontessi.core.address.adapters.out.persistence.repository;

import io.github.educontessi.core.address.adapters.out.persistence.entity.StreetEntity;
import io.github.educontessi.core.address.adapters.out.persistence.repository.customquery.StreetRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StreetRepository extends JpaRepository<StreetEntity, Long>, StreetRepositoryQuery {

    Optional<StreetEntity> findByNameAndCityId(String name, Long cityId);

    List<StreetEntity> findAllByCityId(Long cityId);

}
