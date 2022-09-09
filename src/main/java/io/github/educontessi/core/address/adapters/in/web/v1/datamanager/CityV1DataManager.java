package io.github.educontessi.core.address.adapters.in.web.v1.datamanager;

import io.github.educontessi.core.address.adapters.in.web.v1.dataconverter.CityInV1DataConverter;
import io.github.educontessi.core.address.adapters.in.web.v1.dto.CityV1Dto;
import io.github.educontessi.core.address.core.filter.CityFilter;
import io.github.educontessi.core.address.core.model.City;
import io.github.educontessi.core.address.core.ports.in.CityUseCasePort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class CityV1DataManager {

    private final CityUseCasePort cityUseCasePort;
    private final CityInV1DataConverter dataConverter;

    public CityV1DataManager(CityUseCasePort cityUseCasePort, CityInV1DataConverter dataConverter) {
        this.cityUseCasePort = cityUseCasePort;
        this.dataConverter = dataConverter;
    }

    public Page<CityV1Dto> search(CityFilter filter, Pageable pageable, String expand) {
        Page<City> paginated = (Page<City>) cityUseCasePort.search(filter, pageable, expand);
        return new PageImpl<>(
                paginated.getContent().stream().map(dataConverter::convertToDto).toList(),
                paginated.getPageable(), paginated.getTotalElements());
    }

    public CityV1Dto findById(Long id, String expand) {
        var model = cityUseCasePort.findById(id, expand);
        return dataConverter.convertToDto(model);
    }

    public List<CityV1Dto> findAllByStateId(Long stateId, String expand) {
        List<City> list = cityUseCasePort.findAllByStateId(stateId, expand);
        return list.stream().map(dataConverter::convertToDto).toList();
    }

    public CityV1Dto findByIbge(Integer ibge, String expand) {
        var model = cityUseCasePort.findByIbge(ibge, expand);
        return dataConverter.convertToDto(model);
    }

    public CityV1Dto save(CityV1Dto dto) {
        var model = new City();
        dataConverter.copyToModel(model, dto);

        model = cityUseCasePort.save(model, Collections.emptyList());
        return dataConverter.convertToDto(model);
    }

    public CityV1Dto update(Long id, CityV1Dto dto) {
        var model = new City();
        dataConverter.copyToModel(model, dto);

        model = cityUseCasePort.update(id, model, Collections.emptyList());
        return dataConverter.convertToDto(dto, model);
    }

    public void delete(Long id) {
        cityUseCasePort.delete(id, Collections.emptyList());
    }

}
