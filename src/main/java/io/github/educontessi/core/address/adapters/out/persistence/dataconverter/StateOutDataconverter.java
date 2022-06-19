package io.github.educontessi.core.address.adapters.out.persistence.dataconverter;

import io.github.educontessi.core.address.adapters.out.persistence.entity.StateEntity;
import io.github.educontessi.core.address.core.model.State;
import io.github.educontessi.core.address.core.util.ExpandUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class StateOutDataconverter implements DataConverter<StateEntity, State> {

    @Override
    public void modelToEntity(StateEntity entity, State model) {
        BeanUtils.copyProperties(model, entity);
    }

    @Override
    public State entityToModel(StateEntity entity, String expand) {
        State model = new State();
        BeanUtils.copyProperties(entity, model);
        setCountry(model, entity, expand);
        return model;
    }

    public State entityToModel(StateEntity entity) {
        return entityToModel(entity, null);
    }

    protected void setCountry(State model, StateEntity entity, String expand) {
        if (ExpandUtil.contains("country", expand)) {
            model.setCountry(new CountryOutDataconverter().entityToModel(entity.getCountry()));
            model.setCountryId(null); // otimizar retorno json
        } else {
            model.setCountryId(entity.getCountryId());
        }
    }

}
