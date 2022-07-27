package io.github.educontessi.core.address.adapters.in.web.v1.dataconverter;

import io.github.educontessi.core.address.adapters.in.web.v1.dto.CityV1Dto;
import io.github.educontessi.core.address.core.model.City;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class CityInV1DataConverter extends DataConverter<City, CityV1Dto> {

    @Override
    public void copyToModel(City model, CityV1Dto dto) {
        BeanUtils.copyProperties(dto, model);
        model.setStateId(getIdOrNull(dto.getState(), dto.getStateId()));
        isValid(model);
    }

    @Override
    public CityV1Dto convertToDto(CityV1Dto dto, City model) {
        BeanUtils.copyProperties(model, dto);
        if(model.getState() != null){
            dto.setState(new StateInV1DataConverter().convertToDto(model.getState()));
            dto.setStateId(null); // otimização do json de retorno
        }
        return dto;
    }

    public CityV1Dto convertToDto(City model) {
        return convertToDto(new CityV1Dto(), model);
    }

}
