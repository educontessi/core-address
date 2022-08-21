package io.github.educontessi.core.address.adapters.out.persistence.repository;

import io.github.educontessi.core.address.adapters.out.persistence.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, Long>{

    List<AddressEntity> findAllByIntegrationId(String integrationId);

    Optional<AddressEntity> findByIntegrationIdAndDefaultAddressIsTrue(String integrationId);

    Optional<AddressEntity> findByIdAndIntegrationId(Long id, String integrationId);
}
