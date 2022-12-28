package io.github.educontessi.core.address.adapters.in.rest.v1.dataconverter;

import io.github.educontessi.core.address.adapters.in.rest.v1.dto.AddressV1Dto;
import io.github.educontessi.core.address.core.model.Address;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class AddressInV1DataConverter extends DataConverter<Address, AddressV1Dto> {

    @Override
    public void copyToModel(Address model, AddressV1Dto dto) {
        BeanUtils.copyProperties(dto, model);
        model.setCountryId(getIdOrNull(dto.getCountry(), dto.getCountryId()));
        model.setStateId(getIdOrNull(dto.getState(), dto.getStateId()));
        model.setCityId(getIdOrNull(dto.getCity(), dto.getCityId()));
        model.setNeighborhoodId(getIdOrNull(dto.getNeighborhood(), dto.getNeighborhoodId()));
        model.setStateId(getIdOrNull(dto.getStreet(), dto.getStreetId()));
        isValid(model);
    }

    @Override
    public AddressV1Dto convertToDto(AddressV1Dto dto, Address model) {
        BeanUtils.copyProperties(model, dto);
        setCountry(dto, model);
        setState(dto, model);
        setCity(dto, model);
        setNeighborhood(dto, model);
        setStreet(dto, model);
        return dto;
    }

    private void setCountry(AddressV1Dto dto, Address model) {
        if (model.getCountry() != null) {
            dto.setCountry(new CountryInV1DataConverter().convertToDto(model.getCountry()));
            dto.setCountryId(null); // otimização do json de retorno
        }
    }

    private void setState(AddressV1Dto dto, Address model) {
        if (model.getState() != null) {
            dto.setState(new StateInV1DataConverter().convertToDto(model.getState()));
            dto.setStateId(null); // otimização do json de retorno
        }
    }

    private void setCity(AddressV1Dto dto, Address model) {
        if (model.getCity() != null) {
            dto.setCity(new CityInV1DataConverter().convertToDto(model.getCity()));
            dto.setCityId(null); // otimização do json de retorno
        }
    }

    private void setNeighborhood(AddressV1Dto dto, Address model) {
        if (model.getNeighborhood() != null) {
            dto.setNeighborhood(new NeighborhoodInV1DataConverter().convertToDto(model.getNeighborhood()));
            dto.setNeighborhoodId(null); // otimização do json de retorno
        }
    }

    private void setStreet(AddressV1Dto dto, Address model) {
        if (model.getStreet() != null) {
            dto.setStreet(new StreetInV1DataConverter().convertToDto(model.getStreet()));
            dto.setStreetId(null); // otimização do json de retorno
        }
    }

    public AddressV1Dto convertToDto(Address model) {
        return convertToDto(new AddressV1Dto(), model);
    }

}
