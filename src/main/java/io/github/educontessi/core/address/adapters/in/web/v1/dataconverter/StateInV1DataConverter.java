package io.github.educontessi.core.address.adapters.in.web.v1.dataconverter;

import io.github.educontessi.core.address.adapters.in.web.v1.dto.StateV1Dto;
import io.github.educontessi.core.address.core.model.State;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class StateInV1DataConverter extends DataConverter<State, StateV1Dto> {

    @Override
    public void copyToModel(State model, StateV1Dto dto) {
        BeanUtils.copyProperties(dto, model);
        model.setCountryId(getIdOrNull(dto.getCountry(), dto.getCountryId()));
        isValid(model);
    }

    @Override
    public StateV1Dto convertToDto(StateV1Dto dto, State model) {
        BeanUtils.copyProperties(model, dto);
        setCountry(dto, model);
        return dto;
    }

    private void setCountry(StateV1Dto dto, State model) {
        if (model.getCountry() != null) {
            dto.setCountry(new CountryInV1DataConverter().convertToDto(model.getCountry()));
            dto.setCountryId(null); // otimização do json de retorno
        }
    }

    public StateV1Dto convertToDto(State model) {
        return convertToDto(new StateV1Dto(), model);
    }

}
