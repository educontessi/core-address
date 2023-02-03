package io.github.educontessi.core.address.adapters.in.rest.v1.controller;

import io.github.educontessi.core.address.adapters.in.rest.documentation.*;
import io.github.educontessi.core.address.adapters.in.rest.v1.datamanager.CityV1DataManager;
import io.github.educontessi.core.address.adapters.in.rest.v1.dto.CityV1Dto;
import io.github.educontessi.core.address.core.filter.CityFilter;
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
        value = "/v1/cities",
        produces = {"application/json"},
        consumes = {"application/json"})
@Tag(name = "CityV1Controller", description = "City Manipulation")
public class CityV1Controller extends BaseController<CityV1Dto> {

    private final CityV1DataManager dataManager;

    public CityV1Controller(CityV1DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @PostMapping
    @SwaggerDocumentationPOST
    public ResponseEntity<CityV1Dto> save(@Valid @RequestBody CityV1Dto dto) {
        CityV1Dto saved = dataManager.save(dto);
        return created(saved.getId(), saved);
    }

    @GetMapping("/search")
    @SwaggerDocumentationGETList
    public ResponseEntity<Page<CityV1Dto>> search(CityFilter filter, Pageable pageable, String expand) {
        Page<CityV1Dto> page = dataManager.search(filter, pageable, expand);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    @SwaggerDocumentationGET
    public ResponseEntity<CityV1Dto> findById(@PathVariable Long id, String expand) {
        CityV1Dto dto = dataManager.findById(id, expand);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/state/{stateId}")
    @SwaggerDocumentationGETList
    public ResponseEntity<List<CityV1Dto>> findAllByStateId(@PathVariable Long stateId, String expand) {
        List<CityV1Dto> list = dataManager.findAllByStateId(stateId, expand);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/ibge/{ibge}")
    @SwaggerDocumentationGET
    public ResponseEntity<CityV1Dto> findByIbge(@PathVariable Integer ibge, String expand) {
        CityV1Dto dto = dataManager.findByIbge(ibge, expand);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    @SwaggerDocumentationPUT
    public ResponseEntity<CityV1Dto> update(@PathVariable Long id, @Valid @RequestBody CityV1Dto dto) {
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
