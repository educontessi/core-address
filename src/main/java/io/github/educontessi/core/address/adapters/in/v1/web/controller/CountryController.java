package io.github.educontessi.core.address.adapters.in.v1.web.controller;

import io.github.educontessi.core.address.adapters.in.documentation.*;
import io.github.educontessi.core.address.adapters.in.v1.datamanager.CountryDataManager;
import io.github.educontessi.core.address.adapters.in.v1.dto.CountryDto;
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
public class CountryController extends BaseController<CountryDto> {

    private final CountryDataManager dataManager;

    public CountryController(CountryDataManager dataManager) {
        this.dataManager = dataManager;
    }

    @PostMapping
    @SwaggerDocumentationPOST
    public ResponseEntity<CountryDto> save(@Valid @RequestBody CountryDto dto) {
        CountryDto saved = dataManager.save(dto);
        return created(saved.getId(), saved);
    }

    @GetMapping
    @SwaggerDocumentationGETList
    public ResponseEntity<List<CountryDto>> findAll() {
        List<CountryDto> list = dataManager.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/search")
    @SwaggerDocumentationGETList
    public ResponseEntity<Page<CountryDto>> search(CountryFilter filter, Pageable pageable) {
        Page<CountryDto> page = dataManager.search(filter, pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    @SwaggerDocumentationGET
    public ResponseEntity<CountryDto> findById(@PathVariable Long id) {
        CountryDto dto = dataManager.findById(id);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    @SwaggerDocumentationPUT
    public ResponseEntity<CountryDto> update(@PathVariable Long id, @Valid @RequestBody CountryDto dto) {
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
