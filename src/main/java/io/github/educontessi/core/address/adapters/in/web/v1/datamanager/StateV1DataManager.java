package io.github.educontessi.core.address.adapters.in.web.v1.datamanager;

import io.github.educontessi.core.address.adapters.in.web.v1.dataconverter.StateInV1DataConverter;
import io.github.educontessi.core.address.adapters.in.web.v1.dto.StateV1Dto;
import io.github.educontessi.core.address.core.filter.StateFilter;
import io.github.educontessi.core.address.core.model.State;
import io.github.educontessi.core.address.ports.in.StateUseCasePort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class StateV1DataManager {

    private final StateUseCasePort stateUseCasePort;
    private final StateInV1DataConverter dataConverter;

    public StateV1DataManager(StateUseCasePort stateUseCasePort, StateInV1DataConverter dataConverter) {
        this.stateUseCasePort = stateUseCasePort;
        this.dataConverter = dataConverter;
    }

    public List<StateV1Dto> findAll(String expand) {
        List<State> list = stateUseCasePort.findAll(expand);
        return list.stream().map(dataConverter::convertToDto).toList();
    }

    public Page<StateV1Dto> search(StateFilter filter, Pageable pageable, String expand) {
        Page<State> paginatedTist = (Page<State>) stateUseCasePort.search(filter, pageable, expand);
        return new PageImpl<>(
                paginatedTist.getContent().stream().map(dataConverter::convertToDto).toList(),
                paginatedTist.getPageable(), paginatedTist.getTotalElements());
    }

    public StateV1Dto findById(Long id, String expand) {
        var model = stateUseCasePort.findById(id, expand);
        return dataConverter.convertToDto(model);
    }

    public List<StateV1Dto> findAllByCountryId(Long countryId, String expand) {
        List<State> list = stateUseCasePort.findAllByCountryId(countryId, expand);
        return list.stream().map(dataConverter::convertToDto).toList();
    }

    public StateV1Dto findByUf(String uf, String expand) {
        var model = stateUseCasePort.findByUf(uf, expand);
        return dataConverter.convertToDto(model);
    }

    public StateV1Dto save(StateV1Dto dto) {
        var model = new State();
        dataConverter.copyToModel(model, dto);

        model = stateUseCasePort.save(model, Collections.emptyList());
        return dataConverter.convertToDto(model);
    }

    public StateV1Dto update(Long id, StateV1Dto dto) {
        var model = new State();
        dataConverter.copyToModel(model, dto);

        model = stateUseCasePort.update(id, model, Collections.emptyList());
        return dataConverter.convertToDto(dto, model);
    }

    public void delete(Long id) {
        stateUseCasePort.delete(id, Collections.emptyList());
    }



}
