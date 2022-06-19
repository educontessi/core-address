package io.github.educontessi.core.address.adapters.in.v1.datamanager;

import io.github.educontessi.core.address.adapters.in.v1.dataconverter.StateInDataConverter;
import io.github.educontessi.core.address.adapters.in.v1.dto.StateDto;
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
public class StateDataManager {

    private final StateUseCasePort stateUseCasePort;
    private final StateInDataConverter dataConverter;

    public StateDataManager(StateUseCasePort stateUseCasePort, StateInDataConverter dataConverter) {
        this.stateUseCasePort = stateUseCasePort;
        this.dataConverter = dataConverter;
    }

    public List<StateDto> findAll(String expand) {
        List<State> list = stateUseCasePort.findAll(expand);
        return list.stream().map(dataConverter::convertToDto).toList();
    }

    public Page<StateDto> search(StateFilter filter, Pageable pageable, String expand) {
        Page<State> paginatedTist = (Page<State>) stateUseCasePort.search(filter, pageable, expand);
        return new PageImpl<>(
                paginatedTist.getContent().stream().map(dataConverter::convertToDto).toList(),
                paginatedTist.getPageable(), paginatedTist.getTotalElements());
    }

    public StateDto findById(Long id, String expand) {
        var model = stateUseCasePort.findById(id, expand);
        return dataConverter.convertToDto(model);
    }

    public StateDto save(StateDto dto) {
        var model = new State();
        dataConverter.copyToModel(model, dto);

        model = stateUseCasePort.save(model, Collections.emptyList());
        return dataConverter.convertToDto(model);
    }

    public StateDto update(Long id, StateDto dto) {
        var model = new State();
        dataConverter.copyToModel(model, dto);

        model = stateUseCasePort.update(id, model, Collections.emptyList());
        return dataConverter.convertToDto(dto, model);
    }

    public void delete(Long id) {
        stateUseCasePort.delete(id, Collections.emptyList());
    }
}
