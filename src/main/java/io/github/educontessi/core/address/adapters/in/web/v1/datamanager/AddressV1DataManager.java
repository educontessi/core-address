package io.github.educontessi.core.address.adapters.in.web.v1.datamanager;

import io.github.educontessi.core.address.adapters.in.web.v1.dataconverter.AddressInV1DataConverter;
import io.github.educontessi.core.address.adapters.in.web.v1.dto.AddressV1Dto;
import io.github.educontessi.core.address.core.model.Address;
import io.github.educontessi.core.address.core.ports.in.AddressUseCasePort;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class AddressV1DataManager {

    private final AddressUseCasePort cityUseCasePort;
    private final AddressInV1DataConverter dataConverter;

    public AddressV1DataManager(AddressUseCasePort cityUseCasePort, AddressInV1DataConverter dataConverter) {
        this.cityUseCasePort = cityUseCasePort;
        this.dataConverter = dataConverter;
    }

    public List<AddressV1Dto> findAllByIntegrationId(String integrationId) {
        List<Address> list = cityUseCasePort.findAllByIntegrationId(integrationId);
        return list.stream().map(dataConverter::convertToDto).toList();
    }

    public AddressV1Dto findById(String integrationId, Long id) {
        var model = cityUseCasePort.findById(id, integrationId);
        return dataConverter.convertToDto(model);
    }

    public AddressV1Dto findDefaultAddress(String integrationId) {
        var model = cityUseCasePort.findDefaultAddress(integrationId);
        return dataConverter.convertToDto(model);
    }

    public AddressV1Dto save(String integrationId, AddressV1Dto dto) {
        var model = new Address();
        dataConverter.copyToModel(model, dto);
        model.setIntegrationId(integrationId);

        model = cityUseCasePort.save(model, Collections.emptyList());
        return dataConverter.convertToDto(model);
    }

    public AddressV1Dto update(String integrationId, Long id, AddressV1Dto dto) {
        var model = new Address();
        dataConverter.copyToModel(model, dto);
        model.setIntegrationId(integrationId);

        model = cityUseCasePort.update(id, model, Collections.emptyList());
        return dataConverter.convertToDto(dto, model);
    }

    public void delete(String integrationId, Long id) {
        cityUseCasePort.delete(id, integrationId, Collections.emptyList());
    }

}
