package io.github.educontessi.core.address.adapters.out.persistence.dataconverter;

import io.github.educontessi.core.address.adapters.out.persistence.entity.NeighborhoodEntity;
import io.github.educontessi.core.address.core.model.Neighborhood;
import io.github.educontessi.core.address.core.util.ExpandUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class NeighborhoodOutDataconverter implements DataConverter<NeighborhoodEntity, Neighborhood> {

    @Override
    public void modelToEntity(NeighborhoodEntity entity, Neighborhood model) {
        BeanUtils.copyProperties(model, entity);
    }

    @Override
    public Neighborhood entityToModel(NeighborhoodEntity entity, String expand) {
        Neighborhood model = new Neighborhood();
        BeanUtils.copyProperties(entity, model);
        setCity(model, entity, expand);
        return model;
    }

    public Neighborhood entityToModel(NeighborhoodEntity entity) {
        return entityToModel(entity, null);
    }

    protected void setCity(Neighborhood model, NeighborhoodEntity entity, String expand) {
        if (ExpandUtil.contains("state", expand)) {
            model.setCity(new CityOutDataconverter().entityToModel(entity.getCity(), ExpandUtil.extractSubExpand("state", expand)));
            model.setCityId(null); // otimizar retorno json
        } else {
            model.setCityId(entity.getCityId());
        }
    }

}
