package io.github.educontessi.core.address.adapters.in.web.v1.dataconverter;

import io.github.educontessi.core.address.adapters.in.web.v1.dto.ZipCodeSearchV1Dto;
import io.github.educontessi.core.address.adapters.in.web.v1.dto.ZipCodeSearchV1Dto;
import io.github.educontessi.core.address.core.model.ZipCodeSearch;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ZipCodeSearchInV1DataConverter  {
    
    public ZipCodeSearchV1Dto convertToDto(ZipCodeSearch model) {
        ZipCodeSearchV1Dto dto = new ZipCodeSearchV1Dto();
        BeanUtils.copyProperties(model, dto);
        if (model.getState() != null) {
            dto.setState(new StateInV1DataConverter().convertToDto(model.getState()));
        }
        if (model.getCity() != null) {
            dto.setCity(new CityInV1DataConverter().convertToDto(model.getCity()));
        }
        if (model.getNeighborhood() != null) {
            dto.setNeighborhood(new NeighborhoodInV1DataConverter().convertToDto(model.getNeighborhood()));
        }
        if (model.getStreet() != null) {
            dto.setStreet(new StreetInV1DataConverter().convertToDto(model.getStreet()));
        }
        return dto;
    }
    
}
