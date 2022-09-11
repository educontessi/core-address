package io.github.educontessi.core.address.mock;

import io.github.educontessi.core.address.core.enums.PropertyType;
import io.github.educontessi.core.address.core.model.*;
import io.github.educontessi.core.address.core.validation.Validator;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;

/**
 * @author Eduardo Possamai Contessi
 */
public class MockFactory {

    public Address getAddress(){
        Address address = new Address();
        address.setId(1L);
        address.setIntegrationId(UUID.randomUUID().toString());
        address.setCountryId(1L);
        address.setStateId(1L);
        address.setCityId(1L);
        address.setNeighborhoodId(1L);
        address.setStreetId(1L);
        address.setNumber("123");
        address.setRecipientName("recipientName");
        address.setRecipientPhone("recipientPhone");
        address.setPropertyType(PropertyType.HOUSE);
        address.setComplement("complement");
        address.setComplement("proximity");
        address.setComplement("deliveryInstructions");
        return address;
    }

    public Optional<Address> getOptionalAddress(){
        return Optional.of(getAddress());
    }

    public List<Address> getAddressList(){
        return Collections.singletonList(getAddress());
    }

    public List<Validator> getValidatorsOutOfCore() {
        return new ArrayList<>();
    }
}
