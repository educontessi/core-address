package io.github.educontessi.core.address.adapters.in.rest.v1.dataconverter;

import io.github.educontessi.core.address.adapters.in.rest.v1.dto.NeighborhoodV1Dto;
import io.github.educontessi.core.address.core.model.Neighborhood;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class NeighborhoodInV1DataConverter extends DataConverter<Neighborhood, NeighborhoodV1Dto> {

    @Override
    public void copyToModel(Neighborhood model, NeighborhoodV1Dto dto) {
        BeanUtils.copyProperties(dto, model);
        model.setCityId(getIdOrNull(dto.getCity(), dto.getCityId()));
        isValid(model);
    }

    @Override
    public NeighborhoodV1Dto convertToDto(NeighborhoodV1Dto dto, Neighborhood model) {
        BeanUtils.copyProperties(model, dto);
        setCity(dto, model);
        return dto;
    }

    private void setCity(NeighborhoodV1Dto dto, Neighborhood model) {
        if (model.getCity() != null) {
            dto.setCity(new CityInV1DataConverter().convertToDto(model.getCity()));
            dto.setCityId(null); // otimização do json de retorno
        }
    }

    public NeighborhoodV1Dto convertToDto(Neighborhood model) {
        return convertToDto(new NeighborhoodV1Dto(), model);
    }

}
