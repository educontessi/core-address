package io.github.educontessi.core.address.adapters.in.v1.dataconverter;

import io.github.educontessi.core.address.adapters.in.v1.dto.CountryDto;
import io.github.educontessi.core.address.core.model.Country;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class CountryInDataConverter extends DataConverter<Country, CountryDto> {

    @Override
    public void copyToModel(Country model, CountryDto dto) {
        BeanUtils.copyProperties(dto, model);
        isValid(model);
    }

    @Override
    public CountryDto convertToDto(CountryDto dto, Country model) {
        BeanUtils.copyProperties(model, dto);
        return dto;
    }

    public CountryDto convertToDto(Country model) {
        return convertToDto(new CountryDto(), model);
    }

}
