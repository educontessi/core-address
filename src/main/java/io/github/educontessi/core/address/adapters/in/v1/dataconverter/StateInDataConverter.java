package io.github.educontessi.core.address.adapters.in.v1.dataconverter;

import io.github.educontessi.core.address.adapters.in.v1.dto.StateDto;
import io.github.educontessi.core.address.core.model.State;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class StateInDataConverter extends DataConverter<State, StateDto> {

    @Override
    public void copyToModel(State model, StateDto dto) {
        BeanUtils.copyProperties(dto, model);
        model.setCountryId(getIdOrNull(dto.getCountry(), dto.getCountryId()));
        isValid(model);
    }

    @Override
    public StateDto convertToDto(StateDto dto, State model) {
        BeanUtils.copyProperties(model, dto);
        if(model.getCountry() != null){
            dto.setCountry(new CountryInDataConverter().convertToDto(model.getCountry()));
            dto.setCountryId(null); // otimização do json de retorno
        }
        return dto;
    }

    public StateDto convertToDto(State model) {
        return convertToDto(new StateDto(), model);
    }

}
