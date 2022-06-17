package io.github.educontessi.core.address.adapters.out.persistence.dataconverter;

import io.github.educontessi.core.address.adapters.out.persistence.entity.BaseEntity;

public interface DataConverter<E extends BaseEntity, M> {

    void modelToEntity(E entity, M model);

    M entityToModel(E entity, String expand);

}
