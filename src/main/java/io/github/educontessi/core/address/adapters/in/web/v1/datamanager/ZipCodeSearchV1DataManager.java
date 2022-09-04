package io.github.educontessi.core.address.adapters.in.web.v1.datamanager;

import io.github.educontessi.core.address.adapters.in.web.v1.dataconverter.ZipCodeSearchInV1DataConverter;
import io.github.educontessi.core.address.adapters.in.web.v1.dto.ZipCodeSearchV1Dto;
import io.github.educontessi.core.address.core.ports.in.ZipCodeSearchUseCasePort;
import org.springframework.stereotype.Component;

@Component
public class ZipCodeSearchV1DataManager {

    private final ZipCodeSearchUseCasePort zipCodeSearchUseCasePort;
    private final ZipCodeSearchInV1DataConverter dataConverter;

    public ZipCodeSearchV1DataManager(ZipCodeSearchUseCasePort zipCodeSearchUseCasePort, ZipCodeSearchInV1DataConverter dataConverter) {
        this.zipCodeSearchUseCasePort = zipCodeSearchUseCasePort;
        this.dataConverter = dataConverter;
    }

    public ZipCodeSearchV1Dto search(String zipCode) {
        var model = zipCodeSearchUseCasePort.execute(zipCode);
        return dataConverter.convertToDto(model);
    }

}
