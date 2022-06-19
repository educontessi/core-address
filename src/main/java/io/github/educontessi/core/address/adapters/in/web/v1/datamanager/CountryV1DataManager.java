package io.github.educontessi.core.address.adapters.in.web.v1.datamanager;

import io.github.educontessi.core.address.adapters.in.web.v1.dataconverter.CountryInV1DataConverter;
import io.github.educontessi.core.address.adapters.in.web.v1.dto.CountryV1Dto;
import io.github.educontessi.core.address.core.filter.CountryFilter;
import io.github.educontessi.core.address.core.model.Country;
import io.github.educontessi.core.address.ports.in.CountryUseCasePort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class CountryV1DataManager {

    private final CountryUseCasePort countryUseCasePort;
    private final CountryInV1DataConverter dataConverter;

    public CountryV1DataManager(CountryUseCasePort countryUseCasePort, CountryInV1DataConverter dataConverter) {
        this.countryUseCasePort = countryUseCasePort;
        this.dataConverter = dataConverter;
    }

    public List<CountryV1Dto> findAll() {
        List<Country> list = countryUseCasePort.findAll();
        return list.stream().map(dataConverter::convertToDto).toList();
    }

    public Page<CountryV1Dto> search(CountryFilter filter, Pageable pageable) {
        Page<Country> paginatedTist = (Page<Country>) countryUseCasePort.search(filter, pageable);
        return new PageImpl<>(
                paginatedTist.getContent().stream().map(dataConverter::convertToDto).toList(),
                paginatedTist.getPageable(), paginatedTist.getTotalElements());
    }

    public CountryV1Dto findById(Long id) {
        var model = countryUseCasePort.findById(id);
        return dataConverter.convertToDto(model);
    }

    public CountryV1Dto save(CountryV1Dto dto) {
        var model = new Country();
        dataConverter.copyToModel(model, dto);

        model = countryUseCasePort.save(model, Collections.emptyList());
        return dataConverter.convertToDto(dto, model);
    }

    public CountryV1Dto update(Long id, CountryV1Dto dto) {
        var model = new Country();
        dataConverter.copyToModel(model, dto);

        model = countryUseCasePort.update(id, model, Collections.emptyList());
        return dataConverter.convertToDto(dto, model);
    }

    public void delete(Long id) {
        countryUseCasePort.delete(id, Collections.emptyList());
    }
}
