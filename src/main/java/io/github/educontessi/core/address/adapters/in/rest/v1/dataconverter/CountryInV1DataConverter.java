package io.github.educontessi.core.address.adapters.in.rest.v1.dataconverter;

import io.github.educontessi.core.address.adapters.in.rest.v1.dto.CountryV1Dto;
import io.github.educontessi.core.address.core.model.Country;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class CountryInV1DataConverter extends DataConverter<Country, CountryV1Dto> {

    @Override
    public void copyToModel(Country model, CountryV1Dto dto) {
        BeanUtils.copyProperties(dto, model);
        isValid(model);
    }

    @Override
    public CountryV1Dto convertToDto(CountryV1Dto dto, Country model) {
        BeanUtils.copyProperties(model, dto);
        return dto;
    }

    public CountryV1Dto convertToDto(Country model) {
        return convertToDto(new CountryV1Dto(), model);
    }

}
