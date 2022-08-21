package io.github.educontessi.core.address.adapters.out.persistence.dataconverter;

import io.github.educontessi.core.address.adapters.out.persistence.entity.StreetEntity;
import io.github.educontessi.core.address.core.model.Street;
import io.github.educontessi.core.address.core.util.ExpandUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class StreetOutDataconverter implements DataConverter<StreetEntity, Street> {

    @Override
    public void modelToEntity(StreetEntity entity, Street model) {
        BeanUtils.copyProperties(model, entity);
    }

    @Override
    public Street entityToModel(StreetEntity entity, String expand) {
        Street model = new Street();
        BeanUtils.copyProperties(entity, model);
        setCity(model, entity, expand);
        return model;
    }

    public Street entityToModel(StreetEntity entity) {
        return entityToModel(entity, null);
    }

    protected void setCity(Street model, StreetEntity entity, String expand) {
        if (ExpandUtil.contains("city", expand)) {
            model.setCity(new CityOutDataconverter().entityToModel(entity.getCity(), ExpandUtil.extractSubExpand("city", expand)));
            model.setCityId(null); // otimizar retorno json
        } else {
            model.setCityId(entity.getCityId());
        }
    }

}
