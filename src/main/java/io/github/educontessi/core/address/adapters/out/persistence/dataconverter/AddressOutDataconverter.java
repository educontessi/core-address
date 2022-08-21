package io.github.educontessi.core.address.adapters.out.persistence.dataconverter;

import io.github.educontessi.core.address.adapters.out.persistence.entity.AddressEntity;
import io.github.educontessi.core.address.core.model.Address;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class AddressOutDataconverter implements DataConverter<AddressEntity, Address> {

    @Override
    public void modelToEntity(AddressEntity entity, Address model) {
        BeanUtils.copyProperties(model, entity);
    }

    @Override
    public Address entityToModel(AddressEntity entity, String expand) {
        Address model = new Address();
        BeanUtils.copyProperties(entity, model);
        setCountry(model, entity);
        setState(model, entity);
        setCity(model, entity);
        setNeighborhood(model, entity);
        setStreet(model, entity);
        return model;
    }

    public Address entityToModel(AddressEntity entity) {
        return entityToModel(entity, null);
    }

    protected void setCountry(Address model, AddressEntity entity) {
        if (entity.getCountry() != null) {
            model.setCountry(new CountryOutDataconverter().entityToModel(entity.getCountry()));
            model.setCountryId(null); // otimizar retorno json
        }
    }

    protected void setState(Address model, AddressEntity entity) {
        if (entity.getState() != null) {
            model.setState(new StateOutDataconverter().entityToModel(entity.getState()));
            model.setStateId(null); // otimizar retorno json
        }
    }

    protected void setCity(Address model, AddressEntity entity) {
        if (entity.getCity() != null) {
            model.setCity(new CityOutDataconverter().entityToModel(entity.getCity()));
            model.setCityId(null); // otimizar retorno json
        }
    }

    protected void setNeighborhood(Address model, AddressEntity entity) {
        if (entity.getNeighborhood() != null) {
            model.setNeighborhood(new NeighborhoodOutDataconverter().entityToModel(entity.getNeighborhood()));
            model.setNeighborhoodId(null); // otimizar retorno json
        }
    }

    protected void setStreet(Address model, AddressEntity entity) {
        if (entity.getStreet() != null) {
            model.setStreet(new StreetOutDataconverter().entityToModel(entity.getStreet()));
            model.setStreetId(null); // otimizar retorno json
        }
    }
}
