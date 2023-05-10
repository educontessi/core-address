package io.github.educontessi.core.address.adapters.in.rest.v1.controller;

import io.github.educontessi.core.address.adapters.in.rest.documentation.*;
import io.github.educontessi.core.address.adapters.in.rest.v1.datamanager.StreetV1DataManager;
import io.github.educontessi.core.address.adapters.in.rest.v1.dto.StreetV1Dto;
import io.github.educontessi.core.address.core.filter.StreetFilter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(
        value = "/v1/streets",
        produces = {"application/json"},
        consumes = {"application/json"})
@Tag(name = "StreetV1Controller", description = "Street Manipulation")
public class StreetV1Controller extends BaseController<StreetV1Dto> {

    private final StreetV1DataManager dataManager;

    public StreetV1Controller(StreetV1DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @PostMapping
    @SwaggerDocumentationPOST
    public ResponseEntity<StreetV1Dto> save(@Valid @RequestBody StreetV1Dto dto) {
        StreetV1Dto saved = dataManager.save(dto);
        return created(saved.getId(), saved);
    }

    @GetMapping("/search")
    @SwaggerDocumentationGETList
    public ResponseEntity<Page<StreetV1Dto>> search(StreetFilter filter, Pageable pageable, String expand) {
        Page<StreetV1Dto> page = dataManager.search(filter, pageable, expand);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    @SwaggerDocumentationGET
    public ResponseEntity<StreetV1Dto> findById(@PathVariable Long id, String expand) {
        StreetV1Dto dto = dataManager.findById(id, expand);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/city/{cityId}")
    @SwaggerDocumentationGETList
    public ResponseEntity<List<StreetV1Dto>> findAllByCityId(@PathVariable Long cityId, String expand) {
        List<StreetV1Dto> list = dataManager.findAllByCityId(cityId, expand);
        return ResponseEntity.ok(list);
    }

    @PutMapping("/{id}")
    @SwaggerDocumentationPUT
    public ResponseEntity<StreetV1Dto> update(@PathVariable Long id, @Valid @RequestBody StreetV1Dto dto) {
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
