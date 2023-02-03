package io.github.educontessi.core.address.adapters.in.rest.v1.controller;

import io.github.educontessi.core.address.adapters.in.rest.documentation.*;
import io.github.educontessi.core.address.adapters.in.rest.v1.datamanager.NeighborhoodV1DataManager;
import io.github.educontessi.core.address.adapters.in.rest.v1.dto.NeighborhoodV1Dto;
import io.github.educontessi.core.address.core.filter.NeighborhoodFilter;
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
        value = "/v1/neighborhoods",
        produces = {"application/json"},
        consumes = {"application/json"})
@Tag(name = "NeighborhoodV1Controller", description = "Neighborhood Manipulation")
public class NeighborhoodV1Controller extends BaseController<NeighborhoodV1Dto> {

    private final NeighborhoodV1DataManager dataManager;

    public NeighborhoodV1Controller(NeighborhoodV1DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @PostMapping
    @SwaggerDocumentationPOST
    public ResponseEntity<NeighborhoodV1Dto> save(@Valid @RequestBody NeighborhoodV1Dto dto) {
        NeighborhoodV1Dto saved = dataManager.save(dto);
        return created(saved.getId(), saved);
    }

    @GetMapping("/search")
    @SwaggerDocumentationGETList
    public ResponseEntity<Page<NeighborhoodV1Dto>> search(NeighborhoodFilter filter, Pageable pageable, String expand) {
        Page<NeighborhoodV1Dto> page = dataManager.search(filter, pageable, expand);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    @SwaggerDocumentationGET
    public ResponseEntity<NeighborhoodV1Dto> findById(@PathVariable Long id, String expand) {
        NeighborhoodV1Dto dto = dataManager.findById(id, expand);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/city/{cityId}")
    @SwaggerDocumentationGETList
    public ResponseEntity<List<NeighborhoodV1Dto>> findAllByCityId(@PathVariable Long cityId, String expand) {
        List<NeighborhoodV1Dto> list = dataManager.findAllByCityId(cityId, expand);
        return ResponseEntity.ok(list);
    }

    @PutMapping("/{id}")
    @SwaggerDocumentationPUT
    public ResponseEntity<NeighborhoodV1Dto> update(@PathVariable Long id, @Valid @RequestBody NeighborhoodV1Dto dto) {
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
