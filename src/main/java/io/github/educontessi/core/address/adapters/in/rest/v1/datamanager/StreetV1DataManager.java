package io.github.educontessi.core.address.adapters.in.rest.v1.datamanager;

import io.github.educontessi.core.address.adapters.in.rest.v1.dataconverter.StreetInV1DataConverter;
import io.github.educontessi.core.address.adapters.in.rest.v1.dto.StreetV1Dto;
import io.github.educontessi.core.address.core.filter.StreetFilter;
import io.github.educontessi.core.address.core.model.Street;
import io.github.educontessi.core.address.core.ports.in.StreetUseCasePort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class StreetV1DataManager {

    private final StreetUseCasePort cityUseCasePort;
    private final StreetInV1DataConverter dataConverter;

    public StreetV1DataManager(StreetUseCasePort cityUseCasePort, StreetInV1DataConverter dataConverter) {
        this.cityUseCasePort = cityUseCasePort;
        this.dataConverter = dataConverter;
    }

    public Page<StreetV1Dto> search(StreetFilter filter, Pageable pageable, String expand) {
        Page<Street> paginated = (Page<Street>) cityUseCasePort.search(filter, pageable, expand);
        return new PageImpl<>(
                paginated.getContent().stream().map(dataConverter::convertToDto).toList(),
                paginated.getPageable(), paginated.getTotalElements());
    }

    public StreetV1Dto findById(Long id, String expand) {
        var model = cityUseCasePort.findById(id, expand);
        return dataConverter.convertToDto(model);
    }

    public List<StreetV1Dto> findAllByCityId(Long cityId, String expand) {
        List<Street> list = cityUseCasePort.findAllByCityId(cityId, expand);
        return list.stream().map(dataConverter::convertToDto).toList();
    }

    public StreetV1Dto save(StreetV1Dto dto) {
        var model = new Street();
        dataConverter.copyToModel(model, dto);

        model = cityUseCasePort.save(model, Collections.emptyList());
        return dataConverter.convertToDto(model);
    }

    public StreetV1Dto update(Long id, StreetV1Dto dto) {
        var model = new Street();
        dataConverter.copyToModel(model, dto);

        model = cityUseCasePort.update(id, model, Collections.emptyList());
        return dataConverter.convertToDto(dto, model);
    }

    public void delete(Long id) {
        cityUseCasePort.delete(id, Collections.emptyList());
    }

}
