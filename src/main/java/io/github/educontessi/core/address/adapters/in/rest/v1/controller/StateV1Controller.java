package io.github.educontessi.core.address.adapters.in.rest.v1.controller;

import io.github.educontessi.core.address.adapters.in.rest.documentation.*;
import io.github.educontessi.core.address.adapters.in.rest.v1.datamanager.StateV1DataManager;
import io.github.educontessi.core.address.adapters.in.rest.v1.dto.StateV1Dto;
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
public class StateV1Controller extends BaseController<StateV1Dto> {

    private final StateV1DataManager dataManager;

    public StateV1Controller(StateV1DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @PostMapping
    @SwaggerDocumentationPOST
    public ResponseEntity<StateV1Dto> save(@Valid @RequestBody StateV1Dto dto) {
        StateV1Dto saved = dataManager.save(dto);
        return created(saved.getId(), saved);
    }

    @GetMapping
    @SwaggerDocumentationGETList
    public ResponseEntity<List<StateV1Dto>> findAll(String expand) {
        List<StateV1Dto> list = dataManager.findAll(expand);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/search")
    @SwaggerDocumentationGETList
    public ResponseEntity<Page<StateV1Dto>> search(StateFilter filter, Pageable pageable, String expand) {
        Page<StateV1Dto> page = dataManager.search(filter, pageable, expand);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    @SwaggerDocumentationGET
    public ResponseEntity<StateV1Dto> findById(@PathVariable Long id, String expand) {
        StateV1Dto dto = dataManager.findById(id, expand);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/country/{countryId}")
    @SwaggerDocumentationGETList
    public ResponseEntity<List<StateV1Dto>> findAllByCountryId(@PathVariable Long countryId, String expand) {
        List<StateV1Dto> list = dataManager.findAllByCountryId(countryId, expand);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/uf/{uf}")
    @SwaggerDocumentationGET
    public ResponseEntity<StateV1Dto> findByUf(@PathVariable String uf, String expand) {
        StateV1Dto dto = dataManager.findByUf(uf, expand);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    @SwaggerDocumentationPUT
    public ResponseEntity<StateV1Dto> update(@PathVariable Long id, @Valid @RequestBody StateV1Dto dto) {
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
