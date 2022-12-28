package io.github.educontessi.core.address.adapters.in.rest.v1.controller;

import io.github.educontessi.core.address.adapters.in.documentation.*;
import io.github.educontessi.core.address.adapters.in.rest.documentation.*;
import io.github.educontessi.core.address.adapters.in.rest.v1.datamanager.CountryV1DataManager;
import io.github.educontessi.core.address.adapters.in.rest.v1.dto.CountryV1Dto;
import io.github.educontessi.core.address.core.filter.CountryFilter;
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
        value = "/v1/countries",
        produces = {"application/json"},
        consumes = {"application/json"})
@Tag(name = "CountryController", description = "Country Manipulation")
public class CountryV1Controller extends BaseController<CountryV1Dto> {

    private final CountryV1DataManager dataManager;

    public CountryV1Controller(CountryV1DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @PostMapping
    @SwaggerDocumentationPOST
    public ResponseEntity<CountryV1Dto> save(@Valid @RequestBody CountryV1Dto dto) {
        CountryV1Dto saved = dataManager.save(dto);
        return created(saved.getId(), saved);
    }

    @GetMapping
    @SwaggerDocumentationGETList
    public ResponseEntity<List<CountryV1Dto>> findAll() {
        List<CountryV1Dto> list = dataManager.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/search")
    @SwaggerDocumentationGETList
    public ResponseEntity<Page<CountryV1Dto>> search(CountryFilter filter, Pageable pageable) {
        Page<CountryV1Dto> page = dataManager.search(filter, pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    @SwaggerDocumentationGET
    public ResponseEntity<CountryV1Dto> findById(@PathVariable Long id) {
        CountryV1Dto dto = dataManager.findById(id);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    @SwaggerDocumentationPUT
    public ResponseEntity<CountryV1Dto> update(@PathVariable Long id, @Valid @RequestBody CountryV1Dto dto) {
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
