package io.github.educontessi.core.address.adapters.in.v1.datamanager;

import io.github.educontessi.core.address.adapters.in.v1.dataconverter.CountryInDataConverter;
import io.github.educontessi.core.address.adapters.in.v1.dto.CountryDto;
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
public class CountryDataManager {

    private final CountryUseCasePort countryUseCasePort;
    private final CountryInDataConverter dataConverter;

    public CountryDataManager(CountryUseCasePort countryUseCasePort, CountryInDataConverter dataConverter) {
        this.countryUseCasePort = countryUseCasePort;
        this.dataConverter = dataConverter;
    }

    public List<CountryDto> findAll() {
        List<Country> list = countryUseCasePort.findAll();
        return list.stream().map(dataConverter::convertToDto).toList();
    }

    public Page<CountryDto> search(CountryFilter filter, Pageable pageable) {
        Page<Country> paginatedTist = (Page<Country>) countryUseCasePort.search(filter, pageable);
        return new PageImpl<>(
                paginatedTist.getContent().stream().map(dataConverter::convertToDto).toList(),
                paginatedTist.getPageable(), paginatedTist.getTotalElements());
    }

    public CountryDto findById(Long id) {
        var model = countryUseCasePort.findById(id);
        return dataConverter.convertToDto(model);
    }

    public CountryDto save(CountryDto dto) {
        var model = new Country();
        dataConverter.copyToModel(model, dto);

        model = countryUseCasePort.save(model, Collections.emptyList());
        return dataConverter.convertToDto(dto, model);
    }

    public CountryDto update(Long id, CountryDto dto) {
        var model = new Country();
        dataConverter.copyToModel(model, dto);

        model = countryUseCasePort.update(id, model, Collections.emptyList());
        return dataConverter.convertToDto(dto, model);
    }

    public void delete(Long id) {
        countryUseCasePort.delete(id, Collections.emptyList());
    }
}
