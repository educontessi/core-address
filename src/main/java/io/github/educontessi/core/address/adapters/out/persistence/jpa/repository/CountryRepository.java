package io.github.educontessi.core.address.adapters.out.persistence.jpa.repository;

import io.github.educontessi.core.address.adapters.out.persistence.jpa.entity.CountryEntity;
import io.github.educontessi.core.address.adapters.out.persistence.jpa.repository.customquery.CountryRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<CountryEntity, Long>, CountryRepositoryQuery {

}
