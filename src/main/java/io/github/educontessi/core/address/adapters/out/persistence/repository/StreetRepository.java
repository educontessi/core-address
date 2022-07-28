package io.github.educontessi.core.address.adapters.out.persistence.repository;

import io.github.educontessi.core.address.adapters.out.persistence.entity.StreetEntity;
import io.github.educontessi.core.address.adapters.out.persistence.repository.customquery.StreetRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StreetRepository extends JpaRepository<StreetEntity, Long>, StreetRepositoryQuery {

    List<StreetEntity> findAllByCityId(Long cityId);

}
