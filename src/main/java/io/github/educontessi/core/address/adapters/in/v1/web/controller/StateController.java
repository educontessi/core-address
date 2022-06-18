package io.github.educontessi.core.address.adapters.in.v1.web.controller;

import io.github.educontessi.core.address.adapters.in.documentation.*;
import io.github.educontessi.core.address.adapters.in.v1.datamanager.StateDataManager;
import io.github.educontessi.core.address.adapters.in.v1.dto.StateDto;
import io.github.educontessi.core.address.core.filter.StateFilter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(
        value = "/v1/states",
        produces = {"application/json"},
        consumes = {"application/json"})
@Tag(name = "StateController", description = "State Manipulation")
public class StateController extends BaseController<StateDto> {

    private final StateDataManager dataManager;

    public StateController(StateDataManager dataManager) {
        this.dataManager = dataManager;
    }

    @PostMapping
    @SwaggerDocumentationPOST
    public ResponseEntity<StateDto> save(@Valid @RequestBody StateDto dto) {
        StateDto saved = dataManager.save(dto);
        return created(saved.getId(), saved);
    }

    @GetMapping
    @SwaggerDocumentationGETList
    public ResponseEntity<List<StateDto>> findAll(String expand) {
        List<StateDto> list = dataManager.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/search")
    @SwaggerDocumentationGETList
    public ResponseEntity<Page<StateDto>> search(StateFilter filter, Pageable pageable) {
        Page<StateDto> page = dataManager.search(filter, pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    @SwaggerDocumentationGET
    public ResponseEntity<StateDto> findById(@PathVariable Long id) {
        StateDto dto = dataManager.findById(id);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    @SwaggerDocumentationPUT
    public ResponseEntity<StateDto> update(@PathVariable Long id, @Valid @RequestBody StateDto dto) {
        dto = dataManager.update(id, dto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    @SwaggerDocumentationDELETE
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        dataManager.delete(id);
    }

}
