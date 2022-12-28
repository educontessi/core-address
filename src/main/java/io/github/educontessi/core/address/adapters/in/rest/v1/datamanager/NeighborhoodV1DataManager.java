package io.github.educontessi.core.address.adapters.in.rest.v1.datamanager;

import io.github.educontessi.core.address.adapters.in.rest.v1.dataconverter.NeighborhoodInV1DataConverter;
import io.github.educontessi.core.address.adapters.in.rest.v1.dto.NeighborhoodV1Dto;
import io.github.educontessi.core.address.core.filter.NeighborhoodFilter;
import io.github.educontessi.core.address.core.model.Neighborhood;
import io.github.educontessi.core.address.core.ports.in.NeighborhoodUseCasePort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class NeighborhoodV1DataManager {

    private final NeighborhoodUseCasePort cityUseCasePort;
    private final NeighborhoodInV1DataConverter dataConverter;

    public NeighborhoodV1DataManager(NeighborhoodUseCasePort cityUseCasePort, NeighborhoodInV1DataConverter dataConverter) {
        this.cityUseCasePort = cityUseCasePort;
        this.dataConverter = dataConverter;
    }

    public Page<NeighborhoodV1Dto> search(NeighborhoodFilter filter, Pageable pageable, String expand) {
        Page<Neighborhood> paginated = (Page<Neighborhood>) cityUseCasePort.search(filter, pageable, expand);
        return new PageImpl<>(
                paginated.getContent().stream().map(dataConverter::convertToDto).toList(),
                paginated.getPageable(), paginated.getTotalElements());
    }

    public NeighborhoodV1Dto findById(Long id, String expand) {
        var model = cityUseCasePort.findById(id, expand);
        return dataConverter.convertToDto(model);
    }

    public List<NeighborhoodV1Dto> findAllByCityId(Long cityId, String expand) {
        List<Neighborhood> list = cityUseCasePort.findAllByCityId(cityId, expand);
        return list.stream().map(dataConverter::convertToDto).toList();
    }

    public NeighborhoodV1Dto save(NeighborhoodV1Dto dto) {
        var model = new Neighborhood();
        dataConverter.copyToModel(model, dto);

        model = cityUseCasePort.save(model, Collections.emptyList());
        return dataConverter.convertToDto(model);
    }

    public NeighborhoodV1Dto update(Long id, NeighborhoodV1Dto dto) {
        var model = new Neighborhood();
        dataConverter.copyToModel(model, dto);

        model = cityUseCasePort.update(id, model, Collections.emptyList());
        return dataConverter.convertToDto(dto, model);
    }

    public void delete(Long id) {
        cityUseCasePort.delete(id, Collections.emptyList());
    }

}
