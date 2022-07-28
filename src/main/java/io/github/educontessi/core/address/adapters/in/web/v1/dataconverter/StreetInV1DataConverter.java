package io.github.educontessi.core.address.adapters.in.web.v1.dataconverter;

import io.github.educontessi.core.address.adapters.in.web.v1.dto.StreetV1Dto;
import io.github.educontessi.core.address.core.model.Street;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class StreetInV1DataConverter extends DataConverter<Street, StreetV1Dto> {

    @Override
    public void copyToModel(Street model, StreetV1Dto dto) {
        BeanUtils.copyProperties(dto, model);
        model.setCityId(getIdOrNull(dto.getCity(), dto.getCityId()));
        isValid(model);
    }

    @Override
    public StreetV1Dto convertToDto(StreetV1Dto dto, Street model) {
        BeanUtils.copyProperties(model, dto);
        if (model.getCity() != null) {
            dto.setCity(new CityInV1DataConverter().convertToDto(model.getCity()));
            dto.setCityId(null); // otimização do json de retorno
        }
        return dto;
    }

    public StreetV1Dto convertToDto(Street model) {
        return convertToDto(new StreetV1Dto(), model);
    }

}
