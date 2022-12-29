package io.github.educontessi.core.address.adapters.out.persistence.jpa.dataconverter;

import io.github.educontessi.core.address.adapters.out.persistence.jpa.entity.CityEntity;
import io.github.educontessi.core.address.core.model.City;
import io.github.educontessi.core.address.core.util.ExpandUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class CityOutDataconverter implements DataConverter<CityEntity, City> {

    @Override
    public void modelToEntity(CityEntity entity, City model) {
        BeanUtils.copyProperties(model, entity);
    }

    @Override
    public City entityToModel(CityEntity entity, String expand) {
        City model = new City();
        BeanUtils.copyProperties(entity, model);
        setState(model, entity, expand);
        return model;
    }

    public City entityToModel(CityEntity entity) {
        return entityToModel(entity, null);
    }

    protected void setState(City model, CityEntity entity, String expand) {
        if (ExpandUtil.contains("state", expand)) {
            model.setState(new StateOutDataconverter().entityToModel(entity.getState(), ExpandUtil.extractSubExpand("state", expand)));
            model.setStateId(null); // otimizar retorno json
        } else {
            model.setStateId(entity.getStateId());
        }
    }

}
